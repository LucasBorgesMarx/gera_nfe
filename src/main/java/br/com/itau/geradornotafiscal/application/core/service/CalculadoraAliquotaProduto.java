package br.com.itau.geradornotafiscal.application.core.service;


import br.com.itau.geradornotafiscal.application.core.domain.Item;
import br.com.itau.geradornotafiscal.application.core.domain.ItemNotaFiscal;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraAliquotaProduto {

    private static final List<ItemNotaFiscal> itemNotaFiscalList = new ArrayList<>();

    public List<ItemNotaFiscal> calcularAliquota(List<Item> items,
                                                 double aliquotaPercentual) {

        for (Item item : items) {
            double valorTributo = item.getValorUnitario() * aliquotaPercentual;

            ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal(
                    item.getIdItem(),
                    item.getDescricao(),
                    item.getValorUnitario(),
                    item.getQuantidade(),
                    valorTributo
            );

            itemNotaFiscalList.add(itemNotaFiscal);
        }
        return itemNotaFiscalList;
    }
}



