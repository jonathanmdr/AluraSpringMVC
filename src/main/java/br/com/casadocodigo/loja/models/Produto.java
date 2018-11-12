package br.com.casadocodigo.loja.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    @Lob
    private String descricao;
    private int paginas;

    @DateTimeFormat
    private Calendar dataLancamento;

    @ElementCollection
    private List<Preco> precos;

}
