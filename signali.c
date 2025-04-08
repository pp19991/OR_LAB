#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <signal.h>
#include <pthread.h>
#include <sys/syscall.h>
#include <sys/types.h>
#include <string.h>

void sklop_prekid(int sig);
int max(int arr[]);
void obrada_pr(int sig);
void dodaj(int arr[],int sig);
void obnovi();
void ispis();

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
    struct sigaction act;
    act.sa_flags=0;
    act.sa_handler=obrada_pr;
    
    sigemptyset(&act.sa_mask);
    sigaddset(&act.sa_mask,10);
    sigaction(10,&act,NULL);
    sigaddset(&act.sa_mask,11);
    sigaction(11,&act,NULL);
    sigaddset(&act.sa_mask,12);
    sigaction(12,&act,NULL);
    sigaddset(&act.sa_mask,13);
    sigaction(13,&act,NULL);
    sigaddset(&act.sa_mask,14);
    sigaction(14,&act,NULL);
    printf("Pokretanje glavne dretve s TID=%ld\n", (long) syscall(SYS_gettid));
    int i=1;
    while(1){
        printf("Program: iteracija %d\n", i++);
        sleep(1);
    }
    
    return 0;
}
void *Sklop(void *x){
    printf("Pokretanje sklop dretve s TID=%ld\n", (long) syscall(SYS_gettid));
    //signali
    struct sigaction act;
    act.sa_flags=SA_NODEFER;
    act.sa_handler=sklop_prekid;
    
    sigemptyset(&act.sa_mask);
    sigaction(SIGHUP,&act,NULL);
    sigaction(SIGINT,&act,NULL);
    sigaction(SIGQUIT,&act,NULL);
    sigaction(SIGILL,&act,NULL);
    sigaction(SIGTRAP,&act,NULL);
    while(1){
        if(max(K_Z)>max(T_P)){
            int m=max(K_Z);
            K_Z[m-1]=0;
            ispis();
            pthread_kill(thr_id[0],m+9);

        }
    }

}

int main() 
{
    printf("Pokretanje procesa s PID=%ld\n", (long) getpid());
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
    printf("Signal: %d\n",sig);
    K_Z[sig-1]=1;
    ispis();
}
int max(int arr[]){
    int i=0;
    int j=0;
    for(i;i<5;i++){
        if(arr[i]==1){
            j=i+1;
        }
    }
    return j;
}
void obrada_pr(int sig){
    
    dodaj(T_P,sig-9);
    memset(T_P,0,sizeof(T_P));
    T_P[sig-10]=1;
    ispis();
    int i=1;
    printf("Pocetak obrade signala %d\n", sig-9);
    for(i;i<=15;i++){
        printf("Obrada signala prioriteta %d: %d/15\n", sig-9, i);
        sleep(1);
    }
    printf("Kraj obrade signala %d\n", sig-9);
    obnovi();
    ispis();
    
}
void dodaj(int arr[],int sig){
    struct clan* clan1=malloc(sizeof(struct clan));
    clan1->p=max(T_P);
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
void ispis(){
    printf("Stanje: ");
    printf("T_P-");
    int i;
    for(i=4;i>=0;i--){
        printf("%d",T_P[i]);
    }
    printf(", ");
    printf("K_Z-");
    for(i=4;i>=0;i--){
        printf("%d",K_Z[i]);
    }
    printf(", ");
    printf("Stog-");
    struct clan* pmc=vrh;
    while(pmc!=NULL){
        for(i=4;i>=0;i--){
            printf("%d",pmc->T_P[i]);
        }
        printf(", ");
        printf("reg[%d] ",pmc->p);
        pmc=pmc->sljedeci;
    }
    printf("\n");
}