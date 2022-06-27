#IGESFirstTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -remoteProjects snapshots1.csv -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'

$result = Select-String -path output.txt -pattern "Class: UserManager CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
$pass = $true
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Project: IGESFirstTest.git CC = 3 CCR = 0.42857142857142855 CCE = 1.0 CME = 1.0 CSCR = 0.0 SCCR = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: UserManager CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Manager CA = 1 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 1.0 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: User CA = 4 CM = 10 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.6666666666666666 CAI = 0.3"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Spec CA = 1 CM = 3 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.06976744186046512 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: SpecsManager CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: UserDao CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: SpecDao CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 1 Passato"
} else {
    "Test 1 Fallito"
}
rm output.txt

#IGESSecondTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -remoteProjects snapshots2.csv -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'
$pass = $true

$result = Select-String -path output.txt -pattern "Project: IGESSecondTest.git CC = 3 CCR = 0.42857142857142855 CCE = 1.0 CME = 1.0 CSCR = 0.0 SCCR = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Patient CA = 5 CM = 13 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = true CMR = 0.6190476190476191 CAI = 0.38461538461538464"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: HealthWorkerManager CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: HealthWorker CA = 5 CM = 13 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.5652173913043478 CAI = 0.38461538461538464"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: PatientManager CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Swab CA = 2 CM = 7 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.6363636363636364 CAI = 0.7142857142857143"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: HealthController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: PatientController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 2 Passato"
} else {
    "Test 2 Fallito"
}
rm output.txt

#IGESThirdTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESThirdTest -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'
$pass = $true

$result = Select-String -path output.txt -pattern "Project: IGESThirdTest CC = 0 CCR = 0.0 CCE = 0.0 CME = 0.0 CSCR = 0.0 SCCR = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: GuessingGame CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 3 Passato"
} else {
    "Test 3 Fallito"
}
rm output.txt

#IGESFourthTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFourthTest -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'
$pass = $true

$result = Select-String -path output.txt -pattern "Project: IGESFourthTest CC = 1 CCR = 0.3333333333333333 CCE = 1.0 CME = 1.0 CSCR = 0.0 SCCR = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Gameplay CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: MapGenerator CA = 1 CM = 1 CIVA = 1.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.5 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Main CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 4 Passato"
} else {
    "Test 4 Fallito"
}
rm output.txt

#IGESFifthTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESFifthTest -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'
$pass = $true

$result = Select-String -path output.txt -pattern "Project: IGESFifthTest CC = 1 CCR = 0.5 CCE = 1.0 CME = 1.0 CSCR = 0.0 SCCR = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: BankApplication CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: BankAccount CA = 1 CM = 1 CIVA = 1.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.25 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 5 Passato"
} else {
    "Test 5 Fallito"
}
rm output.txt

#IGESSixtTest
java -jar target/surface-1.0-SNAPSHOT-jar-with-dependencies.jar CLI -metrics CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR -project src/main/resources/IGESSixthTest -export csv 1> output.txt
(Get-Content 'output.txt') -join ' ' | Set-Content 'output.txt'
$pass = $true

$result = Select-String -path output.txt -pattern "Project: IGESSixthTest CC = 9 CCR = 0.3103448275862069 CCE = 1.0 CME = 1.0 CSCR = 0.0 SCCR = 0.5555555555555556"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: CaseEditriciServiceImpl CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LibroDTO CA = 2 CM = 3 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.2 CAI = 0.5"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LocazioneRest CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: Libro CA = 1 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.14285714285714285 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LibroServiceImpl CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: AutoreServiceImpl CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: CasaEditriceDTO CA = 2 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.3333333333333333 CAI = 0.5"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: ServletInitializer CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LocazioniServiceImpl CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LibroController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: utilCasaEditrice CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LibreriaApplication CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: utilAutore CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LibroRest CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: SwaggerConfig CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: AutoreRest CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: DirtyFixConfig CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = true CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: AutoreDTO CA = 2 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.25 CAI = 0.5"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: CasaEditrice CA = 1 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.3333333333333333 CAI = 1.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: RisultatoDTO CA = 1 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: AutoreController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: CasaEditriceRest CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: utilLibro CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LocazioneController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: CasaEditriceController CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: LocazioneDTO CA = 2 CM = 2 CIVA = 0.0 CCVA = 0.0 CMA = 1.0 RP = false CMR = 0.25 CAI = 0.5"
if ($result -eq $null){
    $pass = $false
}
$result = Select-String -path output.txt -pattern "Class: utilLocazione CA = 0 CM = 0 CIVA = 0.0 CCVA = 0.0 CMA = 0.0 RP = false CMR = 0.0 CAI = 0.0"
if ($result -eq $null){
    $pass = $false
}

if ($pass){
    "Test 6 Passato"
} else {
    "Test 6 Fallito"
}
rm output.txt