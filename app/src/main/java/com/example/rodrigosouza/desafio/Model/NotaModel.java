package com.example.rodrigosouza.desafio.Model;

public class NotaModel {

    private Integer codigo;
    private String  emissor;
    private String  receptor;
    private String  servico;
    private String  valor_total;
    private String  valor_imposto;
    private byte    registroAtivo;


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getValorTotal() {
        return valor_total;
    }

    public void setValorTotal(String valor_total) {
        this.valor_total = valor_total;
    }

    public String getValorImposto() {
        return valor_imposto;
    }

    public void setValorImposto(String valor_imposto) {
        this.valor_imposto = valor_imposto;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }
}
