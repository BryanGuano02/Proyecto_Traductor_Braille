package Traductor;

public class Traductor {

    private Diccionario diccionario;

    public Traductor() {
        diccionario = new Diccionario();
    }

    public String traducirTexto(String textoATraducir) {
        char primerCaracter = '\0';
        String textoTraducido;

        if (!textoATraducir.isEmpty()) {
            primerCaracter = textoATraducir.charAt(0);
        }

        if (esBraille(primerCaracter)) {
            textoTraducido = diccionario.traducirCaracterACaracter(textoATraducir, 0);
        } else {
            textoTraducido = diccionario.traducirCaracterACaracter(textoATraducir, 1);
        }

        return textoTraducido;
    }

    public static boolean esBraille(char primerCaracter) {
        return primerCaracter >= '\u2800' && primerCaracter <= '\u28FF';
    }
}
