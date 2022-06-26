#!/bin/bash

sentinel=1

OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -remoteProjects snapshots1.csv)

ORACLE1="Project: IGESFirstTest.git
CC = 3
CCR = 0.42857142857142855
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0"

ORACLE2="Class: UserManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE3="Class: Spec
CA = 1
CM = 3
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.06976744186046512
CAI = 1.0"

ORACLE4="Class: User
CA = 4
CM = 10
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.6666666666666666
CAI = 0.3"

ORACLE5="Class: UserDao
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE6="Class: Manager
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 1.0
CAI = 1.0"

ORACLE7="Class: SpecsManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE8="Class: SpecDao
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE1"* && "$OUTPUT" == *"$ORACLE2"* &&  "$OUTPUT" == *"$ORACLE3"* &&  "$OUTPUT" == *"$ORACLE4"* &&  "$OUTPUT" == *"$ORACLE5"* &&  "$OUTPUT" == *"$ORACLE6"* &&  "$OUTPUT" == *"$ORACLE7"* &&  "$OUTPUT" == *"$ORACLE8"* ]]; then 
    echo "First Project: All good"
else
    echo "First Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -remoteProjects snapshots2.csv)

ORACLE1="Project: IGESSecondTest.git
CC = 3
CCR = 0.42857142857142855
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0"

ORACLE2="Class: HealthWorker
CA = 5
CM = 13
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.5652173913043478
CAI = 0.38461538461538464"

ORACLE3="Class: PatientController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE4="Class: Swab
CA = 2
CM = 7
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.6363636363636364
CAI = 0.7142857142857143"

ORACLE5="Class: HealthWorkerManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE6="Class: HealthController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE7="Class: Patient
CA = 5
CM = 13
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = true
CMR = 0.6190476190476191
CAI = 0.38461538461538464"

ORACLE8="Class: PatientManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE1"* &&  "$OUTPUT" == *"$ORACLE2"* &&  "$OUTPUT" == *"$ORACLE3"* &&  "$OUTPUT" == *"$ORACLE4"* &&  "$OUTPUT" == *"$ORACLE5"* &&  "$OUTPUT" == *"$ORACLE6"* &&  "$OUTPUT" == *"$ORACLE7"* &&  "$OUTPUT" == *"$ORACLE8"* ]]; then 
    echo "Second Project: All good"
else
    echo "Second Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESThirdTest)

ORACLE1="Project: IGESThirdTest
CC = 0
CCR = 0.0
CCE = 0.0
CME = 0.0
CSCR = 0.0
SCCR = 0.0"

ORACLE2="Class: GuessingGame
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE1"* && "$OUTPUT" == *"$ORACLE2"* ]]; then 
    echo "Third Project: All good"
else
    echo "Third Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFourthTest)

ORACLE1="Project: IGESFourthTest
CC = 1
CCR = 0.3333333333333333
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0"

ORACLE2="Class: Gameplay
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE3="Class: MapGenerator
CA = 1
CM = 1
CIVA = 1.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.5
CAI = 1.0"

ORACLE4="Class: Main
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE1"* &&  "$OUTPUT" == *"$ORACLE2"* &&  "$OUTPUT" == *"$ORACLE3"* &&  "$OUTPUT" == *"$ORACLE4"* ]]; then 
    echo "Fourth Project: All good"
else
    echo "Fourth Project: Not good"
    sentinel=0
fi


OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFifthTest)

ORACLE1="Project: IGESFifthTest
CC = 1
CCR = 0.5
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0"

ORACLE2="Class: BankApplication
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE3="Class: BankAccount
CA = 1
CM = 1
CIVA = 1.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0"

if [[ "$OUTPUT" == *"$ORACLE1"* && "$OUTPUT" == *"$ORACLE2"* && "$OUTPUT" == *"$ORACLE3"* ]]; then 
    echo "Fifth Project: All good"
else
    echo "Fifth Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESSixthTest)

ORACLE1="Project: IGESSixthTest
CC = 9
CCR = 0.3103448275862069
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.5555555555555556"

ORACLE2="Class: LibreriaApplication
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE3="Class: LibroServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE4="Class: LocazioniServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE5="Class: Autore
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0"

ORACLE6="Class: DirtyFixConfig
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = true
CMR = 0.0
CAI = 0.0"

ORACLE7="Class: utilAutore
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE8="Class: CasaEditrice
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.3333333333333333
CAI = 1.0"

ORACLE9="Class: SwaggerConfig
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE10="Class: utilLocazione
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE11="Class: LocazioneController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE12="Class: AutoreServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE13="Class: AutoreRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE14="Class: CasaEditriceController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE15="Class: CasaEditriceRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE16="Class: Libro
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.14285714285714285
CAI = 1.0"

ORACLE17="Class: AutoreDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 0.5"

ORACLE18="Class: LocazioneDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 0.5"

ORACLE19="Class: LibroRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE20="Class: AutoreController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE21="Class: CaseEditriciServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE22="Class: RisultatoDTO
CA = 1
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE23="Class: LibroDTO
CA = 2
CM = 3
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.2
CAI = 0.5"

ORACLE24="Class: utilCasaEditrice
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE25="Class: LibroController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE26="Class: LocazioneRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE27="Class: ServletInitializer
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

ORACLE28="Class: Locazione
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0"

ORACLE29="Class: CasaEditriceDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.3333333333333333
CAI = 0.5"

ORACLE30="Class: utilLibro
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE1"* && "$OUTPUT" == *"$ORACLE2"* && "$OUTPUT" == *"$ORACLE3"* && "$OUTPUT" == *"$ORACLE4"* && "$OUTPUT" == *"$ORACLE5"* && "$OUTPUT" == *"$ORACLE6"* && "$OUTPUT" == *"$ORACLE7"* && "$OUTPUT" == *"$ORACLE8"* && "$OUTPUT" == *"$ORACLE9"* && "$OUTPUT" == *"$ORACLE10"* && "$OUTPUT" == *"$ORACLE11"* && "$OUTPUT" == *"$ORACLE12"* && "$OUTPUT" == *"$ORACLE13"* && "$OUTPUT" == *"$ORACLE14"* && "$OUTPUT" == *"$ORACLE15"* && "$OUTPUT" == *"$ORACLE16"* && "$OUTPUT" == *"$ORACLE17"* && "$OUTPUT" == *"$ORACLE18"* && "$OUTPUT" == *"$ORACLE19"* && "$OUTPUT" == *"$ORACLE20"* && "$OUTPUT" == *"$ORACLE21"* && "$OUTPUT" == *"$ORACLE22"* && "$OUTPUT" == *"$ORACLE23"* && "$OUTPUT" == *"$ORACLE24"* && "$OUTPUT" == *"$ORACLE25"* && "$OUTPUT" == *"$ORACLE26"* && "$OUTPUT" == *"$ORACLE27"* && "$OUTPUT" == *"$ORACLE28"* && "$OUTPUT" == *"$ORACLE29"* && "$OUTPUT" == *"$ORACLE30"* ]]; then 
    echo "Sixth Project: All good"
else
    echo "Sixth Project: Not good"
    sentinel=0
fi

echo ""

if [[ $sentinel == 1 ]]; then
    echo "All tests passed. Tool is working."
else
    echo "Some tests failed."
fi 