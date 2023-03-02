package entities;

import org.w3c.dom.ls.LSOutput;

public class BaseDados {
    private String nome;
    private String sobrenome;
    private String funcao;
    private String empresa;
    private String veiculo;
    private Double peso;
    private Double altura;


    public BaseDados(String nome, String sobrenome, String funcao, String empresa, String veiculo, Double peso,
                     Double altura) {
        super();
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.funcao = funcao;
        this.empresa = empresa;
        this.veiculo = veiculo;
        this.peso = peso;
        this.altura = altura;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public Double isImc(Double peso, Double altura) {
        return peso/((altura/100)*2);
    }

    public boolean isMaisPesado(Double maiorPesoAteOMomento) {
        return maiorPesoAteOMomento!= null && peso >= maiorPesoAteOMomento;
    }

    public boolean isMaisAlto(Double maiorAlturaAteOMomento) {
        return maiorAlturaAteOMomento!= null && altura >= maiorAlturaAteOMomento;
    }

    public Double isDefinirImc(Double imc) {
        if(imc !=null && imc<18.5) {
            return 1.0;
        }else if(imc >=18.5 && imc <=24.9){
            return 2.0;
        }else if(imc >=25.0 && imc <=29.9){
            return 3.0;
        }else if(imc >=30.0 && imc <=39.9){
            return 4.0;
        }else if(imc >=40.0){
            return 5.0;
        }
        return imc;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Sobrenome: " + sobrenome + ", Funcao: " + funcao + ", Empresa: " + empresa
                + ", Veiculo: " + veiculo + ", Peso: " + peso + ", Altura: " + altura ;
    }
}
