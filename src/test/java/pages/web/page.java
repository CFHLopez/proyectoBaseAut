package pages.web;

public interface page {
    static page pageObject(){
        return new pageWeb();
    }
    void validarTitulo (String titulo);
    void viewClickBtn (String nombreBtn,boolean presionar);
    void viewFiltro ();
    void viewTabla ();
    void validarColumnaTabla (String columna);
    void validarDatoColumna (String columna, String valor);
    void validarLink (String nombreLink);
    void presionarLink (String nombreLink);
}
