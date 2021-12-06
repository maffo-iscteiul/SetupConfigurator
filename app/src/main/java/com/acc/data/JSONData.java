package com.acc.data;

import android.util.Log;

import com.acc.car.CarMercedesAmgGT3Evo;
import com.acc.constant.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONData {
    private static final String LOG_TAG = "JSONData";
    private final HashMap<String, String> mJSONData = new HashMap<>();

    public JSONData() {}

    public JSONData(File file) throws  IOException, JSONException, NumberFormatException, NullPointerException {

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

            String response = stringBuilder.toString();
            //Log.e(LOG_TAG, response);

            JSONObject obj = new JSONObject(response);

            getAndSaveJSONData(obj);
            validateJSONData();

        } catch (IOException | NumberFormatException | NullPointerException | JSONException e) {
            Log.e(LOG_TAG,e.toString());
            e.printStackTrace();
            throw new JSONException("Error in JSON Exception");
        }
    }

    public HashMap<String, String> getJSONData() {
        return mJSONData;
    }

    private void validateJSONData() throws JSONException, NumberFormatException {
        //Validates all values inside mJSONData
        boolean isException = false;

        if (!this.mJSONData.isEmpty()) {
            for (Map.Entry<String, String> data : this.mJSONData.entrySet()) {
                if (data.getValue() == null || data.getValue().isEmpty()) {
                    isException = true;
                    break;
                }
                if (data.getKey().compareTo(Constants.CAR_NAME) != 0) {
                    try {
                        Float.valueOf(data.getValue());
                    } catch (NumberFormatException e) {
                        Log.e(LOG_TAG,data.getKey() + data.getValue());
                        throw new NumberFormatException("Something inside JSON Data are not OK!");
                    }
                }
            }
        } else {
            throw new JSONException("Something inside JSON Data are not OK!");
        }

        if (isException) {
            throw new JSONException("Values inside JSON Data are not OK!");
        }
    }

    private void getAndSaveJSONData(JSONObject obj) throws JSONException, NullPointerException {
        try {
            JSONObject basicSetup = obj.getJSONObject("basicSetup");
            JSONObject advancedSetup = obj.getJSONObject("advancedSetup");

            String carName = obj.getString("carName");

            //TYRES
            JSONObject tyres = basicSetup.getJSONObject("tyres");
            String tyreCompound = tyres.get("tyreCompound").toString();

            JSONArray tyrePressure = tyres.optJSONArray("tyrePressure");
            String tyrePressureFL = tyrePressure.get(0).toString();
            String tyrePressureFR = tyrePressure.get(1).toString();
            String tyrePressureRL = tyrePressure.get(2).toString();
            String tyrePressureRR = tyrePressure.get(3).toString();

            JSONObject alignment = basicSetup.getJSONObject("alignment");
            JSONArray camber = alignment.optJSONArray("camber");
            String camberFL = camber.get(0).toString();
            String camberFR = camber.get(1).toString();
            String camberRL = camber.get(2).toString();
            String camberRR = camber.get(3).toString();

            JSONArray toe = alignment.optJSONArray("toe");
            String toeFL = toe.get(0).toString();
            String toeFR = toe.get(1).toString();
            String toeRL = toe.get(2).toString();
            String toeRR = toe.get(3).toString();

            String casterFL = alignment.get("casterLF").toString();
            String casterFR = alignment.get("casterRF").toString();

            String steerRatio = alignment.get("steerRatio").toString();

            //ELECTRONICS
            JSONObject electronics = basicSetup.getJSONObject("electronics");
            String tC1 = electronics.get("tC1").toString();
            String tC2 = electronics.get("tC2").toString();
            String abs = electronics.get("abs").toString();
            String eCUMap = electronics.get("eCUMap").toString();

            //STRATEGY
            JSONObject strategy = basicSetup.getJSONObject("strategy");
            String fuel = strategy.get("fuel").toString();
            String nPitStops = strategy.get("nPitStops").toString();
            String tyreSet = strategy.get("tyreSet").toString();
            String frontBrakePadCompound = strategy.get("frontBrakePadCompound").toString();
            String rearBrakePadCompound = strategy.get("rearBrakePadCompound").toString();
            String fuelPerLap = strategy.get("fuelPerLap").toString();

            //MECHANICAL
            JSONObject mechanicalBalance = advancedSetup.getJSONObject("mechanicalBalance");
            String aRBFront = mechanicalBalance.get("aRBFront").toString();
            String aRBRear = mechanicalBalance.get("aRBRear").toString();
            String brakeTorque = mechanicalBalance.get("brakeTorque").toString();
            String brakeBias = mechanicalBalance.get("brakeBias").toString();

            JSONArray wheelRate = mechanicalBalance.optJSONArray("wheelRate");
            String wheelRateFL = wheelRate.get(0).toString();
            String wheelRateFR = wheelRate.get(1).toString();
            String wheelRateRL = wheelRate.get(2).toString();
            String wheelRateRR = wheelRate.get(3).toString();

            JSONArray bumpStopRate = mechanicalBalance.optJSONArray("bumpStopRateUp");
            String bumpStopRateFL = bumpStopRate.get(0).toString();
            String bumpStopRateFR = bumpStopRate.get(1).toString();
            String bumpStopRateRL = bumpStopRate.get(2).toString();
            String bumpStopRateRR = bumpStopRate.get(3).toString();

            JSONArray bumpStopWindow = mechanicalBalance.optJSONArray("bumpStopWindow");
            String bumpStopWindowFL = bumpStopWindow.get(0).toString();
            String bumpStopWindowFR = bumpStopWindow.get(1).toString();
            String bumpStopWindowRL = bumpStopWindow.get(2).toString();
            String bumpStopWindowRR = bumpStopWindow.get(3).toString();

            JSONObject drivetrain = advancedSetup.getJSONObject("drivetrain");
            String preload = drivetrain.get("preload").toString();

            //DAMPERS
            JSONObject dampers = advancedSetup.getJSONObject("dampers");

            JSONArray bump = dampers.optJSONArray("bumpSlow");
            String bumpFL = bump.get(0).toString();
            String bumpFR = bump.get(1).toString();
            String bumpRL = bump.get(2).toString();
            String bumpRR = bump.get(3).toString();

            JSONArray bumpFast = dampers.optJSONArray("bumpFast");
            String bumpFastFL = bumpFast.get(0).toString();
            String bumpFastFR = bumpFast.get(1).toString();
            String bumpFastRL = bumpFast.get(2).toString();
            String bumpFastRR = bumpFast.get(3).toString();

            JSONArray rebound = dampers.optJSONArray("reboundSlow");
            String reboundFL = rebound.get(0).toString();
            String reboundFR = rebound.get(1).toString();
            String reboundRL = rebound.get(2).toString();
            String reboundRR = rebound.get(3).toString();

            JSONArray reboundFast = dampers.optJSONArray("reboundFast");
            String reboundFastFL = reboundFast.get(0).toString();
            String reboundFastFR = reboundFast.get(1).toString();
            String reboundFastRL = reboundFast.get(2).toString();
            String reboundFastRR = reboundFast.get(3).toString();

            //AERO
            JSONObject aeroBalance = advancedSetup.getJSONObject("aeroBalance");

            JSONArray rideHeight = aeroBalance.optJSONArray("rideHeight");
            String rideHeightF = rideHeight.get(0).toString();
            String rideHeightR = rideHeight.get(2).toString();

            JSONArray brakeDuct = aeroBalance.optJSONArray("brakeDuct");
            String brakeDuctF = brakeDuct.get(0).toString();
            String brakeDuctR = brakeDuct.get(1).toString();

            String rearWing = aeroBalance.get("rearWing").toString();
            String splitter = aeroBalance.get("splitter").toString();

            // Validate and load properties by car

            validateAndLoadConstantsByCar(carName);

            this.mJSONData.put(Constants.CAR_NAME, carName);
            this.mJSONData.put(Constants.TYRE_COMPOUND, tyreCompound);
            this.mJSONData.put(Constants.TYRE_PRESSURE_FL, tyrePressureFL);
            this.mJSONData.put(Constants.TYRE_PRESSURE_FR, tyrePressureFR);
            this.mJSONData.put(Constants.TYRE_PRESSURE_RL, tyrePressureRL);
            this.mJSONData.put(Constants.TYRE_PRESSURE_RR, tyrePressureRR);
            this.mJSONData.put(Constants.CAMBER_FL, camberFL);
            this.mJSONData.put(Constants.CAMBER_FR, camberFR);
            this.mJSONData.put(Constants.CAMBER_RL, camberRL);
            this.mJSONData.put(Constants.CAMBER_RR, camberRR);
            this.mJSONData.put(Constants.TOE_FL, toeFL);
            this.mJSONData.put(Constants.TOE_FR, toeFR);
            this.mJSONData.put(Constants.TOE_RL, toeRL);
            this.mJSONData.put(Constants.TOE_RR, toeRR);
            this.mJSONData.put(Constants.CASTER_FL, casterFL);
            this.mJSONData.put(Constants.CASTER_FR, casterFR);
            this.mJSONData.put(Constants.STEER_RATIO, steerRatio);
            this.mJSONData.put(Constants.TC1, tC1);
            this.mJSONData.put(Constants.TC2, tC2);
            this.mJSONData.put(Constants.ABS, abs);
            this.mJSONData.put(Constants.ECU_MAP, eCUMap);
            this.mJSONData.put(Constants.FUEL, fuel);
            this.mJSONData.put(Constants.NUMBER_PITSTOPS, nPitStops);
            this.mJSONData.put(Constants.TYRE_SET, tyreSet);
            this.mJSONData.put(Constants.FRONT_BRAKE_PAD_COMPOUND, frontBrakePadCompound);
            this.mJSONData.put(Constants.REAR_BRAKE_PAD_COMPOUND, rearBrakePadCompound);
            this.mJSONData.put(Constants.FUEL_PER_LAP, fuelPerLap);
            this.mJSONData.put(Constants.ANTIROLL_BAR_FRONT, aRBFront);
            this.mJSONData.put(Constants.ANTIROLL_BAR_REAR, aRBRear);
            this.mJSONData.put(Constants.BRAKE_TORQUE, brakeTorque);
            this.mJSONData.put(Constants.BRAKE_BIAS, brakeBias);
            this.mJSONData.put(Constants.WHEEL_RATE_FL, wheelRateFL);
            this.mJSONData.put(Constants.WHEEL_RATE_FR, wheelRateFR);
            this.mJSONData.put(Constants.WHEEL_RATE_RL, wheelRateRL);
            this.mJSONData.put(Constants.WHEEL_RATE_RR, wheelRateRR);
            this.mJSONData.put(Constants.BUMP_STOP_RATE_FL, bumpStopRateFL);
            this.mJSONData.put(Constants.BUMP_STOP_RATE_FR, bumpStopRateFR);
            this.mJSONData.put(Constants.BUMP_STOP_RATE_RL, bumpStopRateRL);
            this.mJSONData.put(Constants.BUMP_STOP_RATE_RR, bumpStopRateRR);
            this.mJSONData.put(Constants.BUMP_STOP_WINDOW_FL, bumpStopWindowFL);
            this.mJSONData.put(Constants.BUMP_STOP_WINDOW_FR, bumpStopWindowFR);
            this.mJSONData.put(Constants.BUMP_STOP_WINDOW_RL, bumpStopWindowRL);
            this.mJSONData.put(Constants.BUMP_STOP_WINDOW_RR, bumpStopWindowRR);
            this.mJSONData.put(Constants.PRELOAD, preload);
            this.mJSONData.put(Constants.BUMP_FL, bumpFL);
            this.mJSONData.put(Constants.BUMP_FR, bumpFR);
            this.mJSONData.put(Constants.BUMP_RL, bumpRL);
            this.mJSONData.put(Constants.BUMP_RR, bumpRR);
            this.mJSONData.put(Constants.BUMP_FAST_FL, bumpFastFL);
            this.mJSONData.put(Constants.BUMP_FAST_FR, bumpFastFR);
            this.mJSONData.put(Constants.BUMP_FAST_RL, bumpFastRL);
            this.mJSONData.put(Constants.BUMP_FAST_RR, bumpFastRR);
            this.mJSONData.put(Constants.REBOUND_FL, reboundFL);
            this.mJSONData.put(Constants.REBOUND_FR, reboundFR);
            this.mJSONData.put(Constants.REBOUND_RL, reboundRL);
            this.mJSONData.put(Constants.REBOUND_RR, reboundRR);
            this.mJSONData.put(Constants.REBOUND_FAST_FL, reboundFastFL);
            this.mJSONData.put(Constants.REBOUND_FAST_FR, reboundFastFR);
            this.mJSONData.put(Constants.REBOUND_FAST_RL, reboundFastRL);
            this.mJSONData.put(Constants.REBOUND_FAST_RR, reboundFastRR);
            this.mJSONData.put(Constants.RIDE_HEIGHT_FRONT, rideHeightF);
            this.mJSONData.put(Constants.RIDE_HEIGHT_REAR, rideHeightR);
            this.mJSONData.put(Constants.BRAKE_DUCT_FRONT, brakeDuctF);
            this.mJSONData.put(Constants.BRAKE_DUCT_REAR, brakeDuctR);
            this.mJSONData.put(Constants.REAR_WING, rearWing);
            this.mJSONData.put(Constants.SPLITTER, splitter);

        } catch (NumberFormatException | NullPointerException e) {
            throw new NumberFormatException("Something inside JSON Data are not OK!");

        }
    }

    private void validateAndLoadConstantsByCar(String carName) throws NullPointerException {
        if (carName.equals(Constants.CAR_NAME_MERCEDES_AMG_GT3_EVO)) {
            new CarMercedesAmgGT3Evo();
        } else {
            // Doesn't exist so should be an error!
            throw new NullPointerException("Car name doesn't exist!");
        }
    }
}
