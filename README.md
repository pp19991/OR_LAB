# Podaci o Bundesligi
Ovaj skup podataka sadrži informacije o klubovima, igračima, trenerima i stadionima Bundeslige za sezonu 2024./2025.
Bundesliga je najviša profesionalna nogometna liga u njemačkoj i održava se od 1963. godine.
## Metapodaci
- **Naziv skupa podataka**: podaci o Bundesliga klubovima, terenima, igračima i trenerima
- **Licencija**: Creative Commons Attribution 4.0 International (CC BY 4.0) -slobodno se može koristiti, djeliti i stvarati prerade uz adekvatno navodenje autora
- **Autor**: Patrik Pašić
- **Verzija**: v1.0
- **Jezik podataka**: hrvatski jezik
- **Dostupni format podataka**: JSON,CSV
- **Datum stvaranja podataka**: 26-10-2024
- **Datum ažuriranja podataka**: 26-10-2024
- **Ključne riječi**: Bundesliga, klub, igrač, trener
- **Učestalost promjene**: podaci se mjenjaju kada se promjeni trener, promjeni stadion, za vrijeme transfera i ako se kasnije u podatke doda trenutni broj bodova onda se nakon svakog kola mjenja
- **Atributi i tablica**:
    - **Stadion**:
        - `naziv`: Naziv stadiona
        - `kapacitet`: Kapacitet stadiona
        - `godina_osnutka`: Godina otvaranja stadiona
    - **Trener**:
        - `id`: Jedinstveni identifikator trenera
        - `ime`: Ime trenera
        - `prezime`: Prezime trenera
    - **Klub**:
        - `naziv`: Naziv kluba
        - `grad`: Grad u kojem se klub nalazi
        - `godina_osnutka`: Godina osnutka kluba
        - `stadion`: Naziv stadiona koji koristi klub
        - `trener`: ID trenera kluba
        - `broj_trofeja_ligi`: Broj osvojenih ligaških trofeja
        - `broj_igraca`: Broj igrača u klubu
        - `prosjek_godina`: Prosječna starost igrača kluba
        - `TM_vrijednost`: Tržišna vrijednost kluba u milijunima prema stranici transfermarkt.com eura
    - **Igraci**:
        - `id`: Jedinstveni identifikator igrača
        - `ime`: Ime igrača
        - `prezime`: Prezime igrača
        - `pozicija`: generalna pozicija na kojoj igra
        - `klub`: Naziv kluba kojem igrač pripada



