import java.math.BigInteger;
import java.util.Random;
class Main{
    public static void main(String args[])
    {
        int seed = 999;
        Random random = new Random(seed);
        int[] vetores = {1000, 10000, 100000, 500000, 1000000};
       
        for (int x: vetores){
            long tempo_total = 0;
            long iteracoes_total= 0;
            long trocas_total= 0;
            long k;
            for (k=1; k<6 ;k++){
                int[] vetor = geraVetorRandom(x, random);
                //int vetor[] = {5,4,2,1,3};
                GnomeSort meuVetor = new GnomeSort();
                long comeco = System.nanoTime();
                meuVetor.sort(vetor,x);
                long fim = System.nanoTime();
                tempo_total = tempo_total + (fim-comeco);
                iteracoes_total = iteracoes_total +  meuVetor.iteracoes;
                trocas_total = trocas_total + meuVetor.trocas;

            }
            System.out.println("--------------------------VETOR: "+x+" ----------------------------");
            System.out.println("tempo medio (nanosegundos) para o sort concluir: "+ tempo_total/k);
            System.out.println("numero medio de iteracoes: "+ iteracoes_total/k);
            System.out.println("numero medio de trocas: "+ trocas_total/k);
        }
 
       
    }
    static int[] geraVetorRandom(int tamanho, Random numero) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = numero.nextInt(10000000);
        }
        return vetor;
    }

}

class GnomeSort{

    long iteracoes;
    long trocas;

    void GnomeSort (){
        this.iteracoes =0;
        this.trocas =0;
    }

    void sort(int vetor[], int length) {
        int i=0;
        while(i<length){
            this.iteracoes++;
            if(i==0){
                i++;
            }
            else{
                if(vetor[i]>=vetor[i-1]){
                    i++;
                }
                else{
                    int atual = vetor[i-1];
                    vetor[i-1]=vetor[i];
                    vetor[i] = atual;
                    i--;
                    this.trocas++;
                }
            }
        }
    }

    void print(int vetor[], int length){
        for(int i=0; i< length; i++){
            System.out.print(vetor[i]+" ");
        }
        System.out.print("\n");
    }
}