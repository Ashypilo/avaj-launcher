package ashypilo;

import ashypilo.Aircraft.AircraftFactory;
import ashypilo.Exception.FileDoesNotExistException;
import ashypilo.Flayable.Flyable;
import ashypilo.Weather.WeatherTower;
import java.io.*;
import java.util.ArrayList;
import ashypilo.Exception.*;

public class Simulator {

    private static File nFile;
    private static PrintWriter out;
    private static AircraftFactory factory;
    private static ArrayList<Flyable> Aircraft;
    private static WeatherTower weathertower;
    private static BufferedReader bf;
    private static Flyable air;
    private static String separator = " ";
    private static String name;
    private static String type;
    private static int count = 0;
    private static int longitude;
    private static int latitude;
    private static int height;
    public static void main(String[] args) throws Exception {

        try {
            if (args.length != 1) {
                throw new FileDoesNotExistException();
            }
            factory = new AircraftFactory();
            Aircraft = new ArrayList<>();
            bf = new BufferedReader(new FileReader(args[0]));
            weathertower = new WeatherTower();
            String line = bf.readLine();
            if (line == null) {
                throw new FileEmptyException();
            }
            if (line.isEmpty()) {
                throw new SimulationRunException();
            }
            for (int i = 0; i < line.length(); i++) {
                if (!Character.isDigit(line.charAt(i))) {
                    throw new SimulationRunException();
                }
            }
            if (count == 0) {
                count = Integer.parseInt(line);
                if (count <= 0) {
                    throw new SimulationRunException();
                }
            }
            line = bf.readLine();
            while (line != null) {
                String[] out_line;
                out_line = line.split(separator);
                for (int i = 0; i < out_line.length; i++) {
                    if (out_line[i].isEmpty()) {
                        throw new WrongFormatException();
                    }
                }
                if (out_line.length != 5) {
                    throw new WrongFormatException();
                }
                type = out_line[0];
                name = out_line[1];
                for (int i = 0; i < out_line[4].length(); i++) {
                    if (!Character.isDigit(out_line[4].charAt(i))) {
                        throw new HeightException(name, type);
                    }
                }
                for (int i = 0; i < out_line[2].length(); i++) {
                    if (!Character.isDigit(out_line[2].charAt(i))) {
                        throw new CoordinateException(name, type);
                    }
                }
                for (int i = 0; i < out_line[3].length(); i++) {
                    if (!Character.isDigit(out_line[3].charAt(i))) {
                        throw new CoordinateException(name, type);
                    }
                }
                longitude = Integer.parseInt(out_line[2]);
                latitude = Integer.parseInt(out_line[3]);
                height = Integer.parseInt(out_line[4]);
                if (height <= 0 || height > 100)
                    throw new HeightException(name, type);
                if (longitude <= 0 || latitude <= 0)
                    throw new CoordinateException(name, type);
                air = factory.newAircraft(type, name, longitude, latitude, height);
                if (air == null)
                    throw new UnknownTypeException();
                Aircraft.add(air);
                line = bf.readLine();
            }
            runSimulation();
        } catch (FileDoesNotExistException e) {
            e.printError();
        } catch (FileEmptyException e) {
            e.printError();
        } catch (HeightException e) {
            e.printError();
        } catch (CoordinateException e) {
            e.printError();
        } catch (UnknownTypeException e) {
            e.printError();
        } catch (SimulationRunException e) {
            e.printError();
        } catch (WrongFormatException e) {
            e.printError();
        }
        catch (NumberFormatException e) {
            System.out.println("Number is not true");
        }
    }

    public static void runSimulation() {
        openFile();
        for (int i = 0; i < Aircraft.size(); i++) {
            Aircraft.get(i).registerTower(weathertower);
        }

        for (int i = 0; i < count; i++) {
            weathertower.changeWeather();
        }
        closeFile();
    }

    public static void openFile() {
        try {

            nFile = new File("simulation.txt");
            nFile.createNewFile();
            out = new PrintWriter(nFile);
        }
        catch (IOException e) {
            System.out.println("File does not create");
        }
    }

    public static void closeFile() {
        out.close();
    }

    public static void writeFile(String line) {
        out.println(line);
    }

}