package br.com.casadocodigo.loja.controllers.test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.casadocodigo.loja.DAO.ProdutoDAO;
import br.com.casadocodigo.loja.controllers.ProdutosController;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class ProdutosControllerTest {

    private ProdutosController subject;

    private static final int ID_PRODUTO = 1;

    @Mock
    private ProdutoDAO produtoDAO;

    @Mock
    private FileSaver fileSaver;

    @Mock
    private WebDataBinder webDataBinder;

    @Mock
    private ModelAndView modelAndView;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private List<Produto> produtos;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        subject = new ProdutosController(produtoDAO, fileSaver);
    }

    @Test
    public void initBinder() {
        subject.initBinder(webDataBinder);
        verify(webDataBinder).addValidators(any(ProdutoValidation.class));
    }

    @Test
    public void form() {
        Produto produto = mock(Produto.class);
        ProdutosController produtosController = mock(ProdutosController.class);

        subject.form(produto);
        modelAndView.addObject("tipos", TipoPreco.values());

        when(produtosController.form(produto)).thenReturn(modelAndView);
    }

    @Test
    public void gravar() {
        Produto produto = mock(Produto.class);
        ProdutosController produtosController = mock(ProdutosController.class);

        when(bindingResult.hasErrors()).thenReturn(true);

        doThrow(NullPointerException.class).when(produtoDAO).gravar(produto);
        subject.gravar(multipartFile, produto, bindingResult, redirectAttributes);

        when(produtosController.gravar(multipartFile, produto, bindingResult, redirectAttributes)).thenReturn(modelAndView);

        expectedException.expect(NullPointerException.class);
        produtoDAO.gravar(produto);
    }

    @Test
    public void listar() {
        ProdutosController produtosController = mock(ProdutosController.class);

        when(produtoDAO.listar()).thenReturn(produtos);

        modelAndView.addObject("produtos", produtos);

        expectedException.expect(NullPointerException.class);
        produtoDAO.listar();

        doThrow(NullPointerException.class).when(produtoDAO).listar();
        subject.listar();

        when(produtosController.listar()).thenReturn(modelAndView);
    }

    @Test
    public void detalhe() {
        Produto produto = mock(Produto.class);
        ProdutosController produtosController = mock(ProdutosController.class);

        when(produtoDAO.find(ID_PRODUTO)).thenReturn(produto);
        modelAndView.addObject(produto);

        when(produtosController.detalhe(ID_PRODUTO)).thenReturn(modelAndView);

        expectedException.expect(NullPointerException.class);
        produtoDAO.find(ID_PRODUTO);

        doThrow(NullPointerException.class).when(produtoDAO).find(ID_PRODUTO);
        subject.detalhe(ID_PRODUTO);
    }
}