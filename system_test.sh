#!/bin/bash

sentinel=1

OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFirstTest)

ORACLE="Project: IGESFirstTest
CC = 3
CCR = 0.42857142857142855
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0

Class: UserManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Spec
CA = 1
CM = 3
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.06976744186046512
CAI = 1.0

Class: User
CA = 4
CM = 10
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.6666666666666666
CAI = 0.3

Class: UserDao
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Manager
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 1.0
CAI = 1.0

Class: SpecsManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: SpecDao
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
    echo "First Project: All good"
else
    echo "First Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESSecondTest)

ORACLE="Project: IGESSecondTest
CC = 3
CCR = 0.42857142857142855
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0

Class: HealthWorker
CA = 5
CM = 13
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.5652173913043478
CAI = 0.38461538461538464

Class: PatientController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Swab
CA = 2
CM = 7
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.6363636363636364
CAI = 0.7142857142857143

Class: HealthWorkerManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: HealthController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Patient
CA = 5
CM = 13
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = true
CMR = 0.6190476190476191
CAI = 0.38461538461538464

Class: PatientManager
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
    echo "Second Project: All good"
else
    echo "Second Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESThirdTest)

ORACLE="Project: IGESThirdTest
CC = 0
CCR = 0.0
CCE = 0.0
CME = 0.0
CSCR = 0.0
SCCR = 0.0

Class: GuessingGame
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
    echo "Third Project: All good"
else
    echo "Third Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFourthTest)

ORACLE="Project: IGESFourthTest
CC = 1
CCR = 0.3333333333333333
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0

Class: Gameplay
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: MapGenerator
CA = 1
CM = 1
CIVA = 1.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.5
CAI = 1.0

Class: Main
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
    echo "Fourth Project: All good"
else
    echo "Fourth Project: Not good"
    sentinel=0
fi


OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFifthTest)

ORACLE="Project: IGESFifthTest
CC = 1
CCR = 0.5
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.0

Class: BankApplication
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: BankAccount
CA = 1
CM = 1
CIVA = 1.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
    echo "Fifth Project: All good"
else
    echo "Fifth Project: Not good"
    sentinel=0
fi

OUTPUT=$(java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESSixthTest)

ORACLE="Project: IGESSixthTest
CC = 9
CCR = 0.3103448275862069
CCE = 1.0
CME = 1.0
CSCR = 0.0
SCCR = 0.5555555555555556

Class: LibreriaApplication
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LibroServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LocazioniServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Autore
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0

Class: DirtyFixConfig
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = true
CMR = 0.0
CAI = 0.0

Class: utilAutore
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: CasaEditrice
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.3333333333333333
CAI = 1.0

Class: SwaggerConfig
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: utilLocazione
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LocazioneController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: AutoreServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: AutoreRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: CasaEditriceController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: CasaEditriceRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Libro
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.14285714285714285
CAI = 1.0

Class: AutoreDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 0.5

Class: LocazioneDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 0.5

Class: LibroRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: AutoreController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: CaseEditriciServiceImpl
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: RisultatoDTO
CA = 1
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LibroDTO
CA = 2
CM = 3
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.2
CAI = 0.5

Class: utilCasaEditrice
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LibroController
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: LocazioneRest
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: ServletInitializer
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0

Class: Locazione
CA = 1
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.25
CAI = 1.0

Class: CasaEditriceDTO
CA = 2
CM = 2
CIVA = 0.0
CCVA = 0.0
CMA = 1.0
RP = false
CMR = 0.3333333333333333
CAI = 0.5

Class: utilLibro
CA = 0
CM = 0
CIVA = 0.0
CCVA = 0.0
CMA = 0.0
RP = false
CMR = 0.0
CAI = 0.0"

if [[ "$OUTPUT" == *"$ORACLE"* ]]; then 
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