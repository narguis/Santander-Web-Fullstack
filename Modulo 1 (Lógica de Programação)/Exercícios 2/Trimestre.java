public enum Trimestre {

    JANEIRO(15000), FEVEREIRO(18500), MARCO(27750);

    private final float gastos;

    Trimestre(float gastos){
        this.gastos = gastos;
    }

    public float getGastos() {
        return gastos;
    }
}