package br.com.itau.geradornotafiscal.adpters.entity;

import java.util.List;


import br.com.itau.geradornotafiscal.adpters.enuns.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.adpters.enuns.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioEntity {
	@JsonProperty("nome")
	private String nome;

	@JsonProperty("tipo_pessoa")
	private TipoPessoa tipoPessoa;

	@JsonProperty("regime_tributacao")
	private RegimeTributacaoPJ regimeTributacao;

	@JsonProperty("documentos")
	private List<DocumentoEntity> documentoEntities;

	@JsonProperty("enderecos")
	private List<EnderecoEntity> enderecoEntities;

}




