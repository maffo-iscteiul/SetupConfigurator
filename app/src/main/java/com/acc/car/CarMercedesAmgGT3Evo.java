package com.acc.car;

import com.acc.constant.AbstractCarConstants;
import com.acc.constant.Constants;

import java.util.ArrayList;
import java.util.List;

public class CarMercedesAmgGT3Evo implements AbstractCarConstants {

    private static final String carName = Constants.CAR_NAME_MERCEDES_AMG_GT3_EVO;

    private static final Float TOE_FRONT_MIN = -0.40F;
    private static final Float TOE_FRONT_MAX = 0.40F;
    private static final Float TOE_REAR_MAX = 0.40F;
    private static final Float TOE_REAR_MIN = -0.40F;
    private static final Float CASTER_MIN = 10.7F;
    private static final Float CASTER_MAX = 16.1F;
    private static final Float TC1_MIN = 0F;
    private static final Float TC1_MAX = 8F;
    private static final Float ABS_MIN = 0F;
    private static final Float ABS_MAX = 11F;
    private static final Float ECU_MAP_MIN = 1F;
    private static final Float ECU_MAP_MAX = 8F;
    private static final Float TC2_MIN = 0F;
    private static final Float TC2_MAX = 8F;
    private static final Float ANTIROLL_BAR_FRONT_MIN = 0F;
    private static final Float ANTIROLL_BAR_FRONT_MAX = 8F;
    private static final Float BRAKE_BIAS_MIN = 57.0F;
    private static final Float BRAKE_BIAS_MAX = 78.0F;
    private static final Float STEER_RATIO_MIN = 14F;
    private static final Float STEER_RATIO_MAX = 18F;
    private static final Float WHEEL_RATE_FRONT_MIN = 115000F;
    private static final Float WHEEL_RATE_FRONT_MAX = 185000F;
    // LIST OF THE NUMBER OF WHAT CLICK REPRESENT IN WHEEL_RATE_NUMBERS - DIFFERENCE THE WHEEL RATE PER CLICK
    private static final List<Float> WHEEL_RATE_FRONT_ITERATE_LIST = new ArrayList<Float>() {{
        add(1000F);
        add(20000F);
    }};
    private static final Float BUMPSTOP_RANGE_FRONT_MIN = 0F;
    private static final Float BUMPSTOP_RANGE_FRONT_MAX = 23F;
    private static final Float WHEEL_RATE_REAR_MIN = 105000F;
    private static final Float WHEEL_RATE_REAR_MAX = 195000F;
    // LIST OF THE NUMBER OF WHAT CLICK REPRESENT IN WHEEL_RATE_NUMBERS - DIFFERENCE THE WHEEL RATE PER CLICK
    private static final List<Float> WHEEL_RATE_REAR_ITERATE_LIST = new ArrayList<Float>() {{
        add(1000F);
        add(20000F);
    }};
    private static final Float BUMPSTOP_RANGE_REAR_MIN = 0F;
    private static final Float BUMPSTOP_RANGE_REAR_MAX = 68F;
    private static final Float ANTIROLL_BAR_REAR_MIN = 0F;
    private static final Float ANTIROLL_BAR_REAR_MAX = 8F;
    private static final Float RIDE_HEIGHT_FRONT_MIN = 55F;
    private static final Float RIDE_HEIGHT_FRONT_MAX = 80F;
    private static final Float RIDE_HEIGHT_REAR_MIN = 55F;
    private static final Float RIDE_HEIGHT_REAR_MAX = 90F;
    private static final Float REAR_WING_MIN = 0F;
    private static final Float REAR_WING_MAX = 10F;


    public CarMercedesAmgGT3Evo() {
        setAllProperties();
    }

    @Override
    public void setAllProperties() {
        Constants.REAL_CAR_NAME = carName;
        Constants.TOE_FRONT_MIN = TOE_FRONT_MIN;
        Constants.TOE_FRONT_MAX = TOE_FRONT_MAX;
        Constants.TOE_REAR_MIN = TOE_REAR_MIN;
        Constants.TOE_REAR_MAX = TOE_REAR_MAX;
        Constants.CASTER_MIN = CASTER_MIN;
        Constants.CASTER_MAX = CASTER_MAX;
        Constants.TC1_MIN = TC1_MIN;
        Constants.TC1_MAX = TC1_MAX;
        Constants.ABS_MIN = ABS_MIN;
        Constants.ABS_MAX = ABS_MAX;
        Constants.ECU_MAP_MIN = ECU_MAP_MIN;
        Constants.ECU_MAP_MAX = ECU_MAP_MAX;
        Constants.TC2_MIN = TC2_MIN;
        Constants.TC2_MAX = TC2_MAX;
        Constants.ANTIROLL_BAR_FRONT_MIN = ANTIROLL_BAR_FRONT_MIN;
        Constants.ANTIROLL_BAR_FRONT_MAX = ANTIROLL_BAR_FRONT_MAX;
        Constants.BRAKE_BIAS_MIN = BRAKE_BIAS_MIN;
        Constants.BRAKE_BIAS_MAX = BRAKE_BIAS_MAX;
        Constants.STEER_RATIO_MIN = STEER_RATIO_MIN;
        Constants.STEER_RATIO_MAX = STEER_RATIO_MAX;
        Constants.WHEEL_RATE_FRONT_MIN = WHEEL_RATE_FRONT_MIN;
        Constants.WHEEL_RATE_FRONT_MAX = WHEEL_RATE_FRONT_MAX;
        Constants.WHEEL_RATE_FRONT_ITERATE_LIST = WHEEL_RATE_FRONT_ITERATE_LIST;
        Constants.BUMPSTOP_RANGE_FRONT_MIN = BUMPSTOP_RANGE_FRONT_MIN;
        Constants.BUMPSTOP_RANGE_FRONT_MAX = BUMPSTOP_RANGE_FRONT_MAX;
        Constants.WHEEL_RATE_REAR_MIN = WHEEL_RATE_REAR_MIN;
        Constants.WHEEL_RATE_REAR_MAX = WHEEL_RATE_REAR_MAX;
        Constants.WHEEL_RATE_REAR_ITERATE_LIST = WHEEL_RATE_REAR_ITERATE_LIST;
        Constants.BUMPSTOP_RANGE_REAR_MIN = BUMPSTOP_RANGE_REAR_MIN;
        Constants.BUMPSTOP_RANGE_REAR_MAX = BUMPSTOP_RANGE_REAR_MAX;
        Constants.ANTIROLL_BAR_REAR_MIN = ANTIROLL_BAR_REAR_MIN;
        Constants.ANTIROLL_BAR_REAR_MAX = ANTIROLL_BAR_REAR_MAX;
        Constants.RIDE_HEIGHT_FRONT_MIN = RIDE_HEIGHT_FRONT_MIN;
        Constants.RIDE_HEIGHT_FRONT_MAX = RIDE_HEIGHT_FRONT_MAX;
        Constants.RIDE_HEIGHT_REAR_MIN = RIDE_HEIGHT_REAR_MIN;
        Constants.RIDE_HEIGHT_REAR_MAX = RIDE_HEIGHT_REAR_MAX;
        Constants.REAR_WING_MIN = REAR_WING_MIN;
        Constants.REAR_WING_MAX = REAR_WING_MAX;
    }
}
