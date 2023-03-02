package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import entities.BaseDados;

public class Program {
    public static void main(String[] args) {

        String path = "c:\\temp\\baseDados.csv"; //Iniciando o csv na variável path;

        List<BaseDados> list=new ArrayList<>();
        List<BaseDados> maiorPeso =new ArrayList<>();
        List<BaseDados> maiorAltura =new ArrayList<>();
        List<BaseDados> imc =new ArrayList<>();
        List<String> pessoasObesas = new ArrayList<>();
        List<String> pessoasLetras =new ArrayList<>();
        Map<String, Integer> mapFuncao = new HashMap<>();
        Map<String, Integer> mapEmpresa = new HashMap<>();
        Map<String, Integer> mapPessoas = new HashMap<>();


        DecimalFormat formatador = new DecimalFormat("0.00");


        try (BufferedReader br = new BufferedReader(new FileReader(path))){ // Criando o buffered e o File Reader puxando a variável path;

            //############ - Lendo o csv e colocando dentro de uma lista - ##############
            String line = br.readLine();    // Fazendo a Leitura da primeira linha do arquivo csv (cabeçalho);
            line = br.readLine();
            while(line!=null) {

                String[] vetor = line.split(";");// função split quebra e separa cada parte do aquivo após cara ";" ;

                //alocando cada palavra separada por ";" numa variável;
                String nome = vetor[0];
                String sobrenome = vetor[1];
                String funcao = vetor[2];
                String empresa = vetor[3];
                String veiculo = vetor[4];
                Double peso = Double.parseDouble(vetor[5]);
                Double altura = Double.parseDouble(vetor[6]);

                BaseDados baseDados = new BaseDados(nome,sobrenome,funcao,empresa,veiculo,peso,altura);
                list.add(baseDados); // Colocando todo o arquivo csv separado por variável dentro de uma lista;


                line=br.readLine();
            }
            //############ - Fim lendo o csv e colocando dentro de uma lista - ##############



            //############ - Pegando o peso - ##############

            Double maiorPesoAtualmente = 0.0;
            for(BaseDados bd: list) { // Percorre a lista;
                if (bd.isMaisPesado(maiorPesoAtualmente)) {
                    maiorPesoAtualmente = bd.getPeso();
                }
            }
            System.out.println();
            System.out.println("O maior peso: "+maiorPesoAtualmente);

            for(BaseDados bd: list) {
                if(maiorPesoAtualmente != null && maiorPesoAtualmente.equals(bd.getPeso())) {
                    maiorPeso.add(bd);
                }
            }
            System.out.println("Todos com o maior peso: ");
            for(BaseDados bdMaiorPeso: maiorPeso) {
                System.out.println(bdMaiorPeso);
            }
            System.out.println();
            //############ - Fim pegando o peso - ##############

            //############ - Pegando a Altura - ##############
            Double maiorAlturaAtualmente = 0.0;
            for(BaseDados bdAlturaAtualmente: list) { // Percorre a lista;
                if (bdAlturaAtualmente.isMaisAlto(maiorAlturaAtualmente)) {
                    maiorAlturaAtualmente = bdAlturaAtualmente.getAltura();
                }
            }// Pega o maior altura;

            System.out.println("A maior Altura: "+maiorAlturaAtualmente);

            for(BaseDados bd: list) {
                if(maiorAlturaAtualmente != null && maiorAlturaAtualmente.equals(bd.getAltura())) {
                    maiorAltura.add(bd);
                }
            }
            System.out.println("Todos com a maior altura: ");

            for(BaseDados bdMaiorAltura: maiorAltura) {
                System.out.println(bdMaiorAltura);
            }
            System.out.println();
            //############ - Fim pegando a Altura - ##############

            //############ - Pegando a imc - ##############
            int magreza =0, normal =0, sobrepeso=0, obesidade=0, obesidadeGrave=0;
            System.out.print("Todas as pessoas com obesidade: ");
            for (BaseDados bd: list){
                Double imc1 = bd.isImc(bd.getPeso(),bd.getAltura());
                Double imc2 = bd.isDefinirImc(imc1);

                if(imc2 == 1.0){
                    magreza+=1;
                }if(imc2 == 2.0){
                    normal+=1;
                }if(imc2 == 3.0){
                    sobrepeso+=1;
                }if(imc2 == 4.0){
                    obesidade+=1;
                    System.out.print(bd.getNome()+", ");
                }if(imc2 == 5.0) {
                    obesidadeGrave += 1;
                }
            }
            System.out.println("\n");
            System.out.println("Imc de todas as pessoas:");
            System.out.println("Magreza: "+magreza
                                +"\nPeso Normal: "+normal
                                +"\nSobrepeso: "+sobrepeso
                                +"\nObesidade: "+obesidade
                                +"\nObesidade grave: "+obesidadeGrave);
            System.out.println("Todas as pessoas obesas: ");


            System.out.println();
            //############ - Fim pegando imc - ##############

            //############ - Pegando função - ##############

            for(BaseDados bd: list) {
                if (mapFuncao.containsKey(bd.getFuncao()) ){
                    mapFuncao.put(bd.getFuncao(),mapFuncao.get(bd.getFuncao())+1);
                }else {
                    mapFuncao.put(bd.getFuncao(),1);
                }
            }
            Integer countFuncao=0;
            String nomeFuncao = " ";
            for (Map.Entry<String,Integer> funcaoMap : mapFuncao.entrySet()){
                if (countFuncao<funcaoMap.getValue()){
                    countFuncao = funcaoMap.getValue();
                    nomeFuncao=funcaoMap.getKey();
                }
            }
            System.out.println("Função mais encontrada: " + nomeFuncao + " = "+ countFuncao);
            System.out.println();

            //############ - Fim pegando função - ##############

            //############ - Pegando Empresa - ##############

            for(BaseDados bd: list) {
                if (mapEmpresa.containsKey(bd.getEmpresa()) ){
                    mapEmpresa.put(bd.getEmpresa(),mapEmpresa.get(bd.getEmpresa())+1);
                }else {
                    mapEmpresa.put(bd.getEmpresa(),1);
                }
            }
            Integer countEmpresa=0;
            String nomeEmpresa = " ";
            for (Map.Entry<String,Integer> empresaMap : mapEmpresa.entrySet()){
                if (countEmpresa<empresaMap.getValue()){
                    countEmpresa = empresaMap.getValue();
                    nomeEmpresa=empresaMap.getKey();
                }
            }
            System.out.println("Empresa mais encontrada: " + nomeEmpresa + " = "+ countEmpresa);
            System.out.println();

            //############ - Fim pegando Empresa - ##############

            //############ - Pegando letra nome pessoa - ##############

            System.out.println("Quantidade de pessoas com a letra inicial em ordem alfabética: ");

            for(BaseDados bd: list) {
                pessoasLetras.add(bd.getNome().substring(0,1));
            }
            Collections.sort(pessoasLetras);

            for(String bd: pessoasLetras) {
                if (mapPessoas.containsKey(bd.substring(0,1)) ){
                    mapPessoas.put(bd.substring(0,1),mapPessoas.get(bd.substring(0,1))+1);
                }else {
                    mapPessoas.put(bd.substring(0,1),1);
                }
            }
            Integer countPessoas=0;
            String nomePessoas = " ";
            for (Map.Entry<String,Integer> pessoasMap : mapPessoas.entrySet()){
                System.out.println(pessoasMap.getKey() +" = "+pessoasMap.getValue());
                }

            //############ - Fim pegando letra nome pessoa - ##############

        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
