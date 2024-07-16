package br.com.itau.geradornotafiscal.config;


import br.com.itau.geradornotafiscal.application.core.domain.Pedido;
import br.com.itau.geradornotafiscal.application.core.enuns.ERegiao;
import org.springframework.stereotype.Component;

@Component
public class Config {

    public static double getValorFreteComPercentual(Pedido pedido, ERegiao ERegiao) {
        double valorFrete = pedido.getValorFrete();
        double valorFreteComPercentual =0;

        if (ERegiao == ERegiao.NORTE) {
            valorFreteComPercentual = valorFrete * 1.08;
        } else if (ERegiao == ERegiao.NORDESTE) {
            valorFreteComPercentual = valorFrete * 1.085;
        } else if (ERegiao == ERegiao.CENTRO_OESTE) {
            valorFreteComPercentual = valorFrete * 1.07;
        } else if (ERegiao == ERegiao.SUDESTE) {
            valorFreteComPercentual = valorFrete * 1.048;
        } else if (ERegiao == ERegiao.SUL) {
            valorFreteComPercentual = valorFrete * 1.06;
        }
        return valorFreteComPercentual;
    }
}
