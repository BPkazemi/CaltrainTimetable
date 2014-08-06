import System.IO
import Data.List.Split
import qualified Data.Map as Map

-- TODO: let the data type 'Tokens' = [String]

data TripInfo = TripInfo { stopId :: String
                         , stopName :: String
                         , tripId :: Int
                         , stopSequence :: Int
                         , bearing :: String
                         , arrivalTime :: String
                         , departureTime :: String
                         } deriving (Show)

main = do
    -- 1. stops.txt         Map stop id's to stop names. Store in map structure
    -- 2. stop_times.txt    Map stop names to stop information. Store in map structure
    -- 3. stop_times.txt    Determine bearing. Add to value for each TripInfo
    -- 4. --------------    Write information to new CSV file
    stopsTxt <- readFile "stops.txt"
    stopTimes <- readFile "stop_times.txt"
    let stopsValues = tail $ readAsCSV stopsTxt
        idsToNames = mapStopIds stopsValues
        stopTimesValues = tail $ readAsCSV stopTimes
        idsToInfo = mapStopInfo stopTimesValues idsToNames
        idsToInfoWithBearing = determineBearing idsToInfo ""
        tripInfoOnly = map (\(stopName, tripInfo) -> tripInfo) idsToInfoWithBearing
    writeFile "stop_info.csv" ""  -- Clear data on each run through
    appendFile "stop_info.csv" "tripId, stopId, stopSequence, bearing, arrivalTime, departureTime"
    sequence $ createCSV tripInfoOnly

{-
 - Desc: Splits a string of csv values into rows, 
 -       and each row into a list of csv values
 - Params: A string containing csv values
 - Return: A list containing lists of csv values. Each element corresponds to one row's csv values
 -}
readAsCSV :: String -> [[String]]
readAsCSV file = 
    let rows = lines file
    in map (\row -> splitOn "," row) rows

createCSV :: [TripInfo] -> [IO ()]
createCSV [] = [appendFile "stop_info.csv" ""]
createCSV (tripInfo:xs) = 
    let tId = show (tripId tripInfo)  
        sId = stopId tripInfo
        sName = stopName tripInfo
        sSequence = show (stopSequence tripInfo)
        direction = bearing tripInfo
        arrival = arrivalTime tripInfo
        departure = departureTime tripInfo
        line = tId ++ ", " ++ sId ++ ", " ++ sName ++ ", " ++ sSequence ++ ", " ++ direction ++ ", " ++ arrival ++ ", " ++ departure ++ "\n"
    in (appendFile "stop_info.csv" line) : createCSV xs

-- TODO: potential fold pattern 
mapStopIds :: [ [String] ] -> Map.Map String String
mapStopIds [] = Map.empty
mapStopIds (rowData : tokenList) = 
    let stopId = rowData !! 0
        stopName = rowData !! 2
    in Map.insert stopId stopName (mapStopIds tokenList)


-- TODO: Sort result by tripId first, then by stopSequence
--       Learn how to override/create the Ord/some new typeclass
--       Add the typeclass to the TripInfo type. 
--       Then sort the list returned here in main.
mapStopInfo :: [ [String] ] -> Map.Map String String -> [(Int, TripInfo)]
mapStopInfo [] _ = [] 
mapStopInfo (rowData : tokenList) idToNameMapping = 
    let tripId = read ((splitOn "-" (rowData !! 0)) !! 0) :: Int
        stopId = rowData !! 3
        stopName = case (Map.lookup stopId idToNameMapping) of
                Just name   -> name
                Nothing     -> ""   -- Will never hit this case
        stopSequence = read (rowData !! 4) :: Int 
        bearing = "not calculated" -- Calculated in later steps
        arrivalTime = rowData !! 1
        departureTime = rowData !! 2
        curTripInfo = TripInfo {
            stopId=stopId, 
            stopName=stopName, 
            tripId=tripId, 
            stopSequence=stopSequence, 
            bearing=bearing, 
            arrivalTime=arrivalTime, 
            departureTime=departureTime }
    in (tripId, curTripInfo) : mapStopInfo tokenList idToNameMapping


-- A trip is defined as > 1 stops
-- For an odd number of stops, sameTrip is true until the final 3 stops, where the bearing is set for the third
-- For an even number of stops, sameTrip is true and a new bearing is determined on each consumption
determineBearing :: [(Int, TripInfo)] -> String -> [(String, TripInfo)]
determineBearing [] _ = []
determineBearing [ (tripId, tripInfo) ] prevBearing = 
    let newTrip = TripInfo {
            stopId=stopId tripInfo,
            stopName=stopName tripInfo,
            tripId=tripId,
            stopSequence=stopSequence tripInfo,
            bearing=prevBearing,
            arrivalTime=arrivalTime tripInfo,
            departureTime=departureTime tripInfo }
    in ( (stopName tripInfo), newTrip) : []
determineBearing ( (tripId1, tripInfo1) : (tripId2, tripInfo2) : xs ) prevBearing = 
    let stopName1 = stopName tripInfo1
        stopName2 = stopName tripInfo2
        sameTrip = (tripId1 == tripId2)
        bearing = case sameTrip of
                True    -> (case (stopId tripInfo2) > (stopId tripInfo1) of
                                True    -> "Southbound"
                                False   -> "Northbound")
                False   -> prevBearing
        newTrip1=TripInfo {
            stopId=stopName1,
            stopName=stopName tripInfo1,
            tripId=tripId1,
            stopSequence=stopSequence tripInfo1,
            bearing=bearing,
            arrivalTime=arrivalTime tripInfo1,
            departureTime=departureTime tripInfo1 }
        newTrip2=TripInfo {
            stopId=stopName2,
            stopName=stopName tripInfo2,
            tripId=tripId2,
            stopSequence=stopSequence tripInfo2,
            bearing=bearing,
            arrivalTime=arrivalTime tripInfo2,
            departureTime=departureTime tripInfo2 }
    in (stopName1, newTrip1) : (stopName2, newTrip2) : (determineBearing xs bearing)
