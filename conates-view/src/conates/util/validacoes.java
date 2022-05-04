package conates.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public final class validacoes {

    public validacoes() {

    }

    public void formatar(String mascara, TextArea componente, int tamanho, int max) {
        if (tamanho < max) {
            String s;

            String saida = mascara.substring(0, 1);

            String texto = mascara.substring(tamanho);

            if (!(texto.substring(0, 1).equals(saida))) {

                s = texto.substring(0, 1);

                componente.appendText(s);

            }
        }
    }

    public boolean campoNumerico(String campo) {
        boolean teste = true;
        //return campo.matches("^[0-9]*$");
        //return campo.matches("^[0-9]*$");
        for (char letra : campo.toCharArray()) {
            if (letra < '0' || letra > '9') {
                teste = false;
            }
        }
        teste = true;
        return teste;
    }

    public boolean verificaData(String nova) {

        return nova.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$");
    }

    public boolean verificaHora(String campo) {
        boolean teste = true;
        int hh = Integer.parseInt(campo.substring(0, 2));
        int mm = Integer.parseInt(campo.substring(3, 5));

        if ((hh < 0 || hh > 23) || (mm < 0 || mm > 59)) {
            teste = false;

            //return campo.matches("^(0[1-9]|[23][0-9]):(0[1-9]|[59][0-9])$");
        }
        return teste;
    }

    public static void addTextLimiter(final TextArea tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

}
