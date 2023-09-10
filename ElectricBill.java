/**
* @author Adam Zieman
* Date: October 21, 2021
* 
* Course: CSCI 241 (Computer Science 1)
* Assignment: 3
*
* Description:
* This program will calculate an electric bill after given various
* inputs from the user. It will display line per line what each
* charge/credit to the final bill is, much like a receipt.
*
* Known bugs: none
*/

import java.util.Scanner;

public class ElectricBill {
    public static void main (String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        // DECLARE CONSTANTS
        final double FACILITIES_RATE = 0.52602;
        final double ON_PEAK_RATE = 0.19625;
        final double OFF_PEAK_RATE = 0.08868;
        final double ENERGY_TOMORROW_RATE = 0.02007;
        final double TAX_CUT_DEFERRED_TAX_RATE = 0.00309;
        final double ENVIRONMENTAL_CONTROL_RATE = 0.00061;
        final double STATE_LOW_INCOME_ASSISTANCE_FEE = 3.15; // dollars
        final int WI_STATE_TAX_RATE = 5; // percent form of rate
        
        
        // PROGRAM HEADING
        System.out.println("Electric Bill Calculation");
        System.out.println("-------------------------");
        
        
        // USER INPUT
        System.out.print("Enter number of days in billing period:\t");
        int numDays = keyboard.nextInt();
        
        System.out.print("Enter previous total meter reading:\t");
        int previousMeterReading = keyboard.nextInt();
        
        System.out.print("Enter current total meter reading:\t");
        int currentMeterReading = keyboard.nextInt();
        
        System.out.print("Enter previous On Peak meter reading:\t");
        int previousPeakReading = keyboard.nextInt();
        
        System.out.print("Enter current On Peak meter reading:\t");
        int currentPeakReading = keyboard.nextInt();
        
        
        // SECTION HEADING - electrity charges/credits
        System.out.println("\nElectricity Charges/Credits:\n");
        
        
        // CALCULATIONS:
        // Calculate Facility Charge
        double facilityCharge = numDays * FACILITIES_RATE;
        
        // calculate Energy - On/Off Peak Charge
        int totalKWH = currentMeterReading - previousMeterReading; // KWH (Kilowatt Hour)
        int onPeakKWH = currentPeakReading - previousPeakReading;
        int offPeakKWH = totalKWH - onPeakKWH;
        double onPeakCharge = onPeakKWH * ON_PEAK_RATE;
        double offPeakCharge = offPeakKWH * OFF_PEAK_RATE; 
        
        // Calculate Energy For Tomorrow - 100%
        double energyTomorrowCharge = totalKWH * ENERGY_TOMORROW_RATE;
        
        // Calculate 2017 Tax Cut-Deffered Tax Credit
        double taxCutDefferedTaxCredit = totalKWH * TAX_CUT_DEFERRED_TAX_RATE;
        
        // Calculate Environmental Control Charge
        double environmentalControlCharge = totalKWH * ENVIRONMENTAL_CONTROL_RATE;
        
        // Calculate WI State Tax
        // excludes State Low Income Assistance Fee
        double sumToTax = facilityCharge + onPeakCharge + offPeakCharge + energyTomorrowCharge - taxCutDefferedTaxCredit + environmentalControlCharge;
        double wiStateTax = sumToTax * WI_STATE_TAX_RATE / 100;
        
        // Calculate Total Electricity Charge
        double totalElectricCharge = sumToTax + wiStateTax + STATE_LOW_INCOME_ASSISTANCE_FEE;
        
        
        // OUTPUT
        // Facilities
        System.out.printf("%-33s%6d%s%-5.5f%10s%.2f\n", "Facilities", numDays, " Days at $", FACILITIES_RATE, "$", facilityCharge);
        
        // Energy - On Peak
        System.out.printf("%-33s%6d%s%-5.5f%11s%.2f\n", "Energy - On Peak", onPeakKWH, " KWH at $", ON_PEAK_RATE, "$", onPeakCharge);
        
        // Energy - Off Peak
        System.out.printf("%-33s%6d%s%-5.5f%11s%.2f\n", "Energy - Off Peak", offPeakKWH, " KWH at $", OFF_PEAK_RATE, "$", offPeakCharge);
        System.out.println();
        
        // Energy for Tomorrow
        System.out.printf("%-33s%6d%s%-5.5f%11s%.2f\n", "Energy For Tomorrow - 100%", totalKWH, " KWH at $", ENERGY_TOMORROW_RATE, "$", energyTomorrowCharge);
        System.out.println();
        
        // 2017 Tax Cut-Deferred Tax Credit
        System.out.printf("%-33s%6d%s%-5.5f%11s%.2f\n", "2017 Tax Cut-Deferred Tax Credit", totalKWH, " KWH at -$", TAX_CUT_DEFERRED_TAX_RATE, "-$", taxCutDefferedTaxCredit);
        
        // Environmental Control Charge
        System.out.printf("%-33s%6d%s%-5.5f%12s%.2f\n", "Environmental Control Charge", totalKWH, " KWH at $", ENVIRONMENTAL_CONTROL_RATE, "$", environmentalControlCharge);
        
        // State Low-Income Assistance Fee
        System.out.printf("%-33s%34s%.2f\n", "State Low-Income Assistance Fee", "$", STATE_LOW_INCOME_ASSISTANCE_FEE);
        System.out.println();
        
        // WI State Tax
        System.out.printf("%-36s%d%s%.2f%18s%.2f\n", "WI State Tax", WI_STATE_TAX_RATE, "% of $", sumToTax, "$",wiStateTax);
        
        // Ekectric Service Total
        System.out.printf("%56s%9s%.2f\n","Electric Service Total:", "$", totalElectricCharge);

        keyboard.close();
    }
}