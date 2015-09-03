package com.example.alvinkalango.showdatrigonometria;

public class Questao {

    private int ID;
    private String QUESTAO;
    private String RESPOSTA;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;

    public Questao() {
        ID = 0;
        QUESTAO = "";
        RESPOSTA = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        OPTD = "";
    }

    public Questao(String questao, String opta, String optb, String optc, String optd, String resposta) {

        QUESTAO = questao;
        RESPOSTA = resposta;
        OPTA = opta;
        OPTB = optb;
        OPTC = optc;
        OPTD = optd;
    }

    public int getID() {
        return this.ID;
    }

    public String getQUESTAO() {
        return this.QUESTAO;
    }

    public String getRESPOSTA() {
        return this.RESPOSTA;
    }

    public String getOPTA() {
        return this.OPTA;
    }

    public String getOPTB() {
        return this.OPTB;
    }

    public String getOPTC() {
        return this.OPTC;
    }

    public String getOPTD() {
        return this.OPTD;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public void setQUESTAO(String questao) {
        this.QUESTAO = questao;
    }

    public void setRESPOSTA(String resposta) {
        this.RESPOSTA = resposta;
    }

    public void setOPTA(String opta) {
        this.OPTA = opta;
    }

    public void setOPTB(String optb) {
        this.OPTB = optb;
    }

    public void setOPTC(String optc) {
        this.OPTC = optc;
    }

    public void setOPTD(String optd) {
        this.OPTD = optd;
    }

}
