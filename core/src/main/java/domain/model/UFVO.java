package domain.model;

public enum UFVO {
    SELECIONE, AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;

    public boolean isSelecionado() {
        return !SELECIONE.equals(this);
    }

    public boolean isNotSelecionado() {
        return !isSelecionado();
    }
}