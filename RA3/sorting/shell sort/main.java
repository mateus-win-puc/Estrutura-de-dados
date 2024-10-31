import java.util.Random;
class Main{
    public static void main(String args[])
    {
        int seed = 999;
        Random random = new Random(seed);
        int[] vetores = {1000, 10000, 100000, 500000, 1000000};
 
        for (int x: vetores){
            long tempo_total =0;
            int iteracoes_total=0;
            int trocas_total=0;
            int k;
            for (k=1; k<6 ;k++){
                int[] vetor = geraVetorRandom(x, random);
                ShellSort meuVetor = new ShellSort();
                long comeco = System.nanoTime();
                meuVetor.sort(vetor, x);
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

class ShellSort{
    int iteracoes;
    int trocas;
    void ShellSort(){
        this.iteracoes =0;
        this.trocas =0;
    }

    int sort(int vetor[], int n) {
        for (int intervalo = n / 2; intervalo > 0; intervalo /= 2) {
            for (int i = intervalo; i < n; i++) {
                int atual = vetor[i];
                int k;
                for (k = i; k >= intervalo && vetor[k - intervalo] > atual; k -= intervalo) {
                    vetor[k] = vetor[k - intervalo];
                    this.trocas++;
                }
                vetor[k] = atual;
            }
            this.iteracoes++;
        }
        return 0;
    }
}