package br.com.itau.geradornotafiscal.adpters.entity;


import br.com.itau.geradornotafiscal.adpters.enuns.Finalidade;
import br.com.itau.geradornotafiscal.adpters.enuns.Regiao;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {
    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("numero")
    private String numero;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("complemento")
    private String complemento;

    @JsonProperty("finalidade")
    private Finalidade finalidade;

    @JsonProperty("regiao")
    private Regiao regiao;
}