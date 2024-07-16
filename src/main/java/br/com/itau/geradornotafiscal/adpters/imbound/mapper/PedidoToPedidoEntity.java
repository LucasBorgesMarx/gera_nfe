package br.com.itau.geradornotafiscal.adpters.imbound.mapper;

import br.com.itau.geradornotafiscal.adpters.entity.*;
import br.com.itau.geradornotafiscal.application.core.domain.*;
import br.com.itau.geradornotafiscal.application.core.enuns.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoToPedidoEntity {

    public Pedido mapper(PedidoEntity pedidoEntity) {
        var pedido = new Pedido(
                pedidoEntity.getIdPedido(),
                pedidoEntity.getData(),
                pedidoEntity.getValorTotalItens(),
                pedidoEntity.getValorFrete(),
                mapperList(pedidoEntity.getItens()),
                mapperDestini(pedidoEntity.getDestinatarioEntity())
        );
        BeanUtils.copyProperties(pedido, pedidoEntity);
        return pedido;
    }

    public List<Item> mapperList(List<ItenEntity> itemEntities) {
        return getItems(itemEntities);
    }

    private List<Item> getItems(List<ItenEntity> itemEntities) {
        List<Item> items = itemEntities.stream()
                .map(itenEntity -> {
                    Item item = new Item();
                    item.setIdItem(itenEntity.getIdItem());
                    item.setDescricao(itenEntity.getDescricao());
                    item.setQuantidade(itenEntity.getQuantidade());
                    item.setValorUnitario(itenEntity.getValorUnitario());
                    return item;
                })
                .collect(Collectors.toList());
        return items;
    }

    public Destinatario mapperDestini(DestinatarioEntity destinatarioEntity) {
        var destinatario = new Destinatario();
        if (Optional.ofNullable(destinatarioEntity.getRegimeTributacao())
                .map(Object::toString).isPresent()){
            destinatario.setNome(destinatarioEntity.getNome());
            destinatario.setTipoPessoa(ETipoPessoa.valueOf(destinatarioEntity.getTipoPessoa().toString()));
            destinatario.setRegimeTributacao(ERegimeTributacaoPJ
                    .valueOf(destinatarioEntity.getRegimeTributacao().toString()));
            destinatario.setDocumentos(mapperDocumentList(destinatarioEntity.getDocumentoEntities()));
            destinatario.setEnderecos(mapperEndList(destinatarioEntity.getEnderecoEntities()));
        }else {
            destinatario.setNome(destinatarioEntity.getNome());
            destinatario.setTipoPessoa(ETipoPessoa.valueOf(destinatarioEntity.getTipoPessoa().toString()));
            destinatario.setDocumentos(mapperDocumentList(destinatarioEntity.getDocumentoEntities()));
            destinatario.setEnderecos(mapperEndList(destinatarioEntity.getEnderecoEntities()));
        }

        BeanUtils.copyProperties(destinatario, destinatarioEntity);
        return destinatario;
    }

    private String teste(String string) {

        return ERegimeTributacaoPJ.valueOf(string).toString();
    }

    public List<Endereco> mapperEndList(List<EnderecoEntity> enderecoEntities) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoEntity enderecoEntity : enderecoEntities) {
            Endereco endereco = new Endereco();
            endereco.setCep(enderecoEntity.getCep());
            endereco.setLogradouro(enderecoEntity.getLogradouro());
            endereco.setNumero(enderecoEntity.getNumero());
            endereco.setEstado(enderecoEntity.getEstado());
            endereco.setComplemento(enderecoEntity.getComplemento());
            endereco.setFinalidade(EFinalidade.valueOf(enderecoEntity.getFinalidade().name()));
            endereco.setRegiao(ERegiao.valueOf(enderecoEntity.getRegiao().name()));
            enderecos.add(endereco);
        }
        return enderecos;
    }

    private List<Documento> mapperDocumentList(List<DocumentoEntity> documentoEntities) {
        List<Documento> documentos = new ArrayList<>();
        for (DocumentoEntity documentoEntity : documentoEntities) {
            Documento documento = new Documento();
            documento.setTipo(ETipoDocumento.valueOf(documentoEntity.getTipo().toString()));
            documento.setNumero(documentoEntity.getNumero());
            documentos.add(documento);
        }
        return documentos;
    }


}
