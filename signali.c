#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <signal.h>
#include <pthread.h>
#include <sys/syscall.h>
#include <sys/types.h>

void sklop_prekid(int sig);
int max(int arr[]);
void obrada_pr(int sig);
void dodaj(int arr[],int sig);
void obnovi();

int K_Z[5];
int T_P[5];
struct clan {
    int T_P[5];
    int p;
    struct clan* sljedeci;
};
struct clan* vrh=NULL;
struct clan* kraj=NULL;
pthread_t thr_id[2];

void *Glavni(void *x){
    
    printf("Pokretanje glavne dretve s TID=%ld", (long) syscall(SYS_gettid));
    int i=1;
    while(1){
        printf("Program: iteracija %d\n", i++);
        sleep(1);
    }
    
    return 0;
}
void *Sklop(void *x){
    printf("Pokretanje sklop dretve s TID=%ld", (long) syscall(SYS_gettid));
    //signali
    struct sigaction act;
    act.sa_handler=sklop_prekid;
    act.sa_flags=SA_NODEFER;
    sigemptyset(&act.sa_mask);
    sigaction(SIGHUP,&act,NULL);
    sigaction(SIGINT,&act,NULL);
    sigaction(SIGQUIT,&act,NULL);
    sigaction(SIGILL,&act,NULL);
    sigaction(SIGTRAP,&act,NULL);
    while(1){

        if(max(K_Z)>max(T_P)){
            int m=max(K_Z)+1;
            act.sa_handler=obrada_pr;
            sigaction(m,&act,NULL);

            K_Z[m-1]=0;
            pthread_kill(thr_id[0],m);


            sigset_t pmc;
            sigemptyset(&pmc);
            sigaddset(&pmc,m);
            pthread_sigmask(SIG_UNBLOCK,&pmc,NULL);
        }
    }

}

int main() 
{
    
    if (pthread_create(&thr_id[0], NULL, Glavni, NULL) != 0) {
        printf("Greska pri stvaranju dretve!\n");
        exit(1);
    }
    sleep(2);
    if (pthread_create(&thr_id[1], NULL, Sklop, NULL) != 0) {
        printf("Greska pri stvaranju dretve!\n");
        exit(1);
    }
    pthread_join(thr_id[0], NULL);
    pthread_join(thr_id[1], NULL);
    return 0;

}
void sklop_prekid(int sig){
    printf("Signal: %d",sig);
    sigset_t stara_maska;
    K_Z[sig-1]=1;
    pthread_sigmask(SIG_SETMASK, NULL, &stara_maska);
    sigaddset(&stara_maska,sig);
    pthread_sigmask(SIG_BLOCK,&stara_maska,NULL);

}
int max(int arr[]){
    int i=0;
    int j=0;
    for(i;i<5;i++){
        if(arr[i]==1){
            j=i;
        }
    }
    return j;
}
void obrada_pr(int sig){
    dodaj(T_P,sig);
    T_P[sig-1]=1;
    int i=1;
    printf("Pocetak obrade signala %d\n", sig);
    for(i;i<=10;i++){
        printf("Obrada signala prioriteta %d: %d/5\n", sig, i);
        sleep(1);
    }
    printf("Kraj obrade signala %d\n", sig);
    obnovi();
    printf("kraj");
}
void dodaj(int arr[],int sig){
    struct clan* clan1=malloc(sizeof(struct clan));
    clan1->p=sig;
    int i=0;
    for(i;i<5;i++){
        clan1->T_P[i]=arr[i];
    }
    clan1->sljedeci=vrh;
    vrh=clan1;
    if(kraj==NULL){
        kraj=clan1;
    }
}
void obnovi(){
    struct clan* clan1=vrh;
    int i=0;
    for(i;i<5;i++){
        T_P[i]=clan1->T_P[i];
    }
    if(vrh->sljedeci==NULL){
        kraj=NULL;
    }
    vrh=vrh->sljedeci;
    free(clan1);
}