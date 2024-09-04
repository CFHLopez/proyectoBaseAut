package pages;

public interface pageComputers {
    static pageComputers pageObject(){
        return new pageComputersWeb();
    }
    void validarTitulo (String titulo);
    void viewClickBtn (String nombreBtn,boolean presionar);
    void viewFiltro ();
    void viewTabla ();
    void validarColumnaTabla (String columna);
    void validarDatoColumna (String columna, String valor);
}
