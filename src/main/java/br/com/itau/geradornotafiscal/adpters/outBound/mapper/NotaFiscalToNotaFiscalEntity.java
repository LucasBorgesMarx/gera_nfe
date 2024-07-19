package br.com.itau.geradornotafiscal.adpters.outBound.mapper;

import br.com.itau.geradornotafiscal.adpters.entity.*;
import br.com.itau.geradornotafiscal.adpters.enuns.*;
import br.com.itau.geradornotafiscal.application.core.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotaFiscalToNotaFiscalEntity {

    public NotaFiscalEntity mapper(NotaFiscal notaFiscal) {
        return  NotaFiscalEntity
                .builder()
                .idNotaFiscal(notaFiscal.getIdNotaFiscal())
                .data(notaFiscal.getData())
                .valorFrete(notaFiscal.getValorFrete())
                .valorTotalItens(notaFiscal.getValorTotalItens())
                .destinatarioEntity(destinatarioEntityMapper(notaFiscal.getDestinatario()))
                .itens(mapperItensList(notaFiscal.getItens())).
                build();

    }
    DestinatarioEntity destinatarioEntityMapper(Destinatario destinatario) {
            if (destinatario.getRegimeTributacao() == null) {
                return DestinatarioEntity
                        .builder()
                        .nome(destinatario.getNome())
                        .tipoPessoa(TipoPessoa.valueOf(destinatario.getTipoPessoa().name()))
                        .documentoEntities(mapperDocumentsList(destinatario.getDocumentos()))
                        .enderecoEntities(mapperEnderecosList(destinatario.getEnderecos()))
                        .build();
            }
            return DestinatarioEntity
                    .builder()
                    .nome(destinatario.getNome())
                    .tipoPessoa(TipoPessoa.valueOf(destinatario.getTipoPessoa().name()))
                    .regimeTributacao(RegimeTributacaoPJ.valueOf(destinatario.getRegimeTributacao().name()))
                    .documentoEntities(mapperDocumentsList(destinatario.getDocumentos()))
                    .enderecoEntities(mapperEnderecosList(destinatario.getEnderecos()))
                    .build();

    }

    private List<EnderecoEntity> mapperEnderecosList(List<Endereco> enderecos){
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecos.forEach(endereco -> {
            var enderecoEntity = EnderecoEntity.builder().
                 cep(endereco.getCep()).
                    logradouro(endereco.getLogradouro()).
                    numero(endereco.getNumero()).
                    estado(endereco.getEstado()).
                    complemento(endereco.getComplemento()).
                    finalidade(Finalidade.valueOf(endereco.getFinalidade().name())).
                    regiao(Regiao.valueOf(endereco.getRegiao().name())).
            build();

            enderecoEntities.add(enderecoEntity);
        });
        return enderecoEntities;
    }

    private List<DocumentoEntity> mapperDocumentsList(List<Documento> documentos) {
            List<DocumentoEntity> documentoEntities = new ArrayList<>();
            documentos.forEach(documento -> {
                var documentoEntity = new DocumentoEntity();
                documentoEntity.setNumero(documento.getNumero());
                documentoEntity.setTipo(TipoDocumento.valueOf(documento.getTipo().name()));
                documentoEntities.add(documentoEntity);
            });
            return documentoEntities;
        }


        private List<ItemNotaFiscalEntity> mapperItensList(List<ItemNotaFiscal> itens) {
        List<ItemNotaFiscalEntity> itemNotaFiscalEntities = new ArrayList<>();
        itens.forEach(itemNotaFiscal -> {
            new ItemNotaFiscalEntity();
            var itemNotaFiscalEntity = ItemNotaFiscalEntity
                    .builder()
                    .idItem(itemNotaFiscal.getIdItem())
                    .descricao(itemNotaFiscal.getDescricao())
                    .valorUnitario(itemNotaFiscal.getValorUnitario())
                    .quantidade(itemNotaFiscal.getQuantidade())
                    .valorTributoItem(itemNotaFiscal.getValorTributoItem())
                    .build();
            itemNotaFiscalEntities.add(itemNotaFiscalEntity);
        });
        return itemNotaFiscalEntities;
    }



}
