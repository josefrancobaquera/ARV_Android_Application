// Team Name: Android Optimizers
// Last Day Modified: November 26, 2019
// Project Name: Autonomous Robotic Vehicle (ARV) Application
// Author(s): Jared Peterson

package com.example.arvwearable;
import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class BuildingPolygons {

    public static void setUpBuildings(GoogleMap map){

        setUpScienceHall(map);
        setUpBiologyAnnex(map);
        setUpAstronomyBuilding(map);
        setUpBransonHallLibrary(map);
        setUpDomeniciHall(map);
        setUpFosterHall(map);
        setUpWaldenHall(map);
        setUpComputerCenter(map);
        setUpYoungHall(map);
        setUpHadleyAdministration(map);
        setUpHardmanJacobsLearningCenter(map);
        setUpGoddardHall(map);
        setUpThomasBrowneHall(map);
        setUpCentralHeatingPlant(map);
        setUpEngineeringComplex(map);
        setUpJettHall(map);
        setUpPyshicalScienceLab(map);
        setUpODonnellHall(map);
        setUpRentfrowHall(map);
        setUpNealeHall(map);
        setUpLivestockJudgingPavilion(map);
        setUpKnoxHall(map);
        setUpMusicBuidling(map);
        setUpChemistryBuidling(map);
        setUpDoveHall(map);
        setUpGuthrieHall(map);
        setUpMiltonHall(map);
        setUpZuhlLibrary(map);
        setUpFrengerFoodCourt(map);
        setUpRegentsRow(map);
        setUpHealthCenter(map);

    }

    // The following functions will simply draw polygons on top of
    // most NMSU buildings. Each function name and tag corresponds
    // to a physical building.

    public static void setUpScienceHall(GoogleMap map){
        Polygon scienceHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280859, -106.751849),
                        new LatLng(32.280636, -106.751793),
                        new LatLng(32.280627, -106.751817),
                        new LatLng(32.280582, -106.751804),
                        new LatLng(32.280585, -106.751777),
                        new LatLng(32.280201, -106.751676),
                        new LatLng(32.280205, -106.751623),
                        new LatLng(32.280000, -106.751576),
                        new LatLng(32.279955, -106.751819),
                        new LatLng(32.280190, -106.751883),
                        new LatLng(32.280187, -106.751930),
                        new LatLng(32.280473, -106.752008),
                        new LatLng(32.280420, -106.752296),
                        new LatLng(32.280462, -106.752304),
                        new LatLng(32.280377, -106.752761),
                        new LatLng(32.280655, -106.752835))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        scienceHall.setTag("Science Hall");

    }

    public static void setUpBiologyAnnex(GoogleMap map){
        Polygon biologyAnnex = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280246, -106.751515),
                        new LatLng(32.280471, -106.751582),
                        new LatLng(32.280549, -106.751133),
                        new LatLng(32.280334, -106.751070))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        biologyAnnex.setTag("Biology Annex");
    }

    public static void setUpAstronomyBuilding(GoogleMap map){
        Polygon astronomyBuilding = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281120, -106.751626),
                        new LatLng(32.281180, -106.751308),
                        new LatLng(32.281006, -106.751248),
                        new LatLng(32.280938, -106.751568))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

         astronomyBuilding.setTag("Astronomy Building");
    }

    public static void setUpBransonHallLibrary(GoogleMap map){
        Polygon bransonHallLibrary = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281512, -106.751384),
                        new LatLng(32.281764, -106.751466),
                        new LatLng(32.281676, -106.751908),
                        new LatLng(32.281764, -106.751939),
                        new LatLng(32.281664, -106.752505),
                        new LatLng(32.281839, -106.752551),
                        new LatLng(32.281813, -106.752697),
                        new LatLng(32.281517, -106.752615),
                        new LatLng(32.281540, -106.752481),
                        new LatLng(32.281331, -106.752420))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        bransonHallLibrary.setTag("Branson Hall Library");

    }

    public static void setUpDomeniciHall(GoogleMap map){

        Polygon domeniciHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281893, -106.751320),
                        new LatLng(32.282014, -106.750698),
                        new LatLng(32.281674, -106.750598),
                        new LatLng(32.281537, -106.751201))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        domeniciHall.setTag("Domenici Hall");


    }

    public static void setUpFosterHall(GoogleMap map){
        Polygon fosterHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281638, -106.753310),
                        new LatLng(32.281736, -106.752804),
                        new LatLng(32.281226, -106.752636),
                        new LatLng(32.281112, -106.753228))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        fosterHall.setTag("Foster Hall");


    }

    public static void setUpWaldenHall(GoogleMap map){
        Polygon waldenHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280816, -106.753279),
                        new LatLng(32.280870, -106.753023),
                        new LatLng(32.280435, -106.752895),
                        new LatLng(32.280378, -106.753167))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

     waldenHall.setTag("Walden Hall");


    }

    public static void setUpComputerCenter(GoogleMap map){
        Polygon computerCenter = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280391, -106.752480),
                        new LatLng(32.280353, -106.752397),
                        new LatLng(32.280367, -106.752304),
                        new LatLng(32.280284, -106.752283),
                        new LatLng(32.280292, -106.752243),
                        new LatLng(32.280237, -106.752228),
                        new LatLng(32.280266, -106.752056),
                        new LatLng(32.279978, -106.751983),
                        new LatLng(32.279933, -106.752013),
                        new LatLng(32.279835, -106.751990),
                        new LatLng(32.279789, -106.752273),
                        new LatLng(32.279732, -106.752316),
                        new LatLng(32.279991, -106.752379),
                        new LatLng(32.279981, -106.752421),
                        new LatLng(32.279968, -106.752427),
                        new LatLng(32.279944, -106.752562),
                        new LatLng(32.280147, -106.752616),
                        new LatLng(32.280187, -106.752429))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        computerCenter.setTag("Computer Center");

    }

    public static void setUpYoungHall(GoogleMap map){

        Polygon youngHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.282225, -106.752259),
                        new LatLng(32.282261, -106.752082),
                        new LatLng(32.281950, -106.751997),
                        new LatLng(32.281919, -106.752173))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        youngHall.setTag("Young Hall");

    }

    public static void setUpHadleyAdministration(GoogleMap map){

        Polygon hadleyAdministration = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.282740, -106.753126),
                        new LatLng(32.282791, -106.752906),
                        new LatLng(32.282254, -106.752747),
                        new LatLng(32.282202, -106.752976))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        hadleyAdministration.setTag("Hadley Administration");

    }

    public static void setUpHardmanJacobsLearningCenter(GoogleMap map){

        Polygon hardmanJacobsLearningCenter = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.282342, -106.751017),
                        new LatLng(32.282756, -106.751122),
                        new LatLng(32.282667, -106.751465),
                        new LatLng(32.282268, -106.751339))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        hardmanJacobsLearningCenter.setTag("Hardman Jacobs Learning Center");


    }
    public static void setUpGoddardHall(GoogleMap map){

        Polygon goddardHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281653, -106.753613),
                        new LatLng(32.281378, -106.753550),
                        new LatLng(32.281251, -106.754218),
                        new LatLng(32.281511, -106.754302))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        goddardHall.setTag("Goddard Hall");
    }

    public static void setUpThomasBrowneHall(GoogleMap map){

        Polygon thomasBrowneHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281283, -106.753522),
                        new LatLng(32.281094, -106.753480),
                        new LatLng(32.280964, -106.754183),
                        new LatLng(32.281153, -106.754232))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        thomasBrowneHall.setTag("Thomas Browne Hall");

    }

    public static void setUpCentralHeatingPlant(GoogleMap map){

        Polygon centralHeatingPlant = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280375, -106.753368),
                        new LatLng(32.280014, -106.753270),
                        new LatLng(32.279899, -106.753826),
                        new LatLng(32.280260, -106.753924))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        centralHeatingPlant.setTag("Central Heating Plant");


    }

    public static void setUpEngineeringComplex(GoogleMap map){

        Polygon engineeringComplex = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280718, -106.754001),
                        new LatLng(32.280535, -106.753952),
                        new LatLng(32.280473, -106.754313),
                        new LatLng(32.280020, -106.754201),
                        new LatLng(32.279458, -106.754208),
                        new LatLng(32.279458, -106.754984),
                        new LatLng(32.279970, -106.754953),
                        new LatLng(32.279931, -106.755149),
                        new LatLng(32.280452, -106.755285))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        engineeringComplex.setTag("Engineering Complex");

    }

    public static void setUpJettHall(GoogleMap map){
        Polygon jettHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281487, -106.754570),
                    new LatLng(32.280861, -106.754409),
                    new LatLng(32.280820, -106.754586),
                    new LatLng(32.281285, -106.754717),
                    new LatLng(32.281249, -106.754913),
                    new LatLng(32.280786, -106.754798),
                    new LatLng(32.280759, -106.754924),
                    new LatLng(32.281219, -106.755045),
                    new LatLng(32.281181, -106.755265),
                    new LatLng(32.280713, -106.755141),
                    new LatLng(32.280679, -106.755318),
                    new LatLng(32.281298, -106.755479))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        jettHall.setTag("Jett Hall");
    }

    public static void setUpPyshicalScienceLab(GoogleMap map){
        Polygon physicalScienceLab = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.278855, -106.754636),
                        new LatLng(32.278943, -106.754164),
                        new LatLng(32.279127, -106.754023),
                        new LatLng(32.279003, -106.753788),
                        new LatLng(32.279100, -106.753305),
                        new LatLng(32.278817, -106.753235),
                        new LatLng(32.278572, -106.754555))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        physicalScienceLab.setTag("Physical Science Lab");
    }

    public static void setUpODonnellHall(GoogleMap map){
        Polygon oDonnellHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.279461, -106.751623),
                        new LatLng(32.279098, -106.751537),
                        new LatLng(32.279059, -106.751779),
                        new LatLng(32.279146, -106.751800),
                        new LatLng(32.279030, -106.752438),
                        new LatLng(32.279239, -106.752497),
                        new LatLng(32.279359, -106.751856),
                        new LatLng(32.279422, -106.751875))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        oDonnellHall.setTag("O'Donnell Hall");
    }

    public static void setUpRentfrowHall(GoogleMap map){
        Polygon rentfrowHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.279239, -106.750156),
                        new LatLng(32.279268, -106.750022),
                        new LatLng(32.279347, -106.750041),
                        new LatLng(32.279413, -106.749713),
                        new LatLng(32.279225, -106.749649),
                        new LatLng(32.279193, -106.749788),
                        new LatLng(32.279080, -106.749762),
                        new LatLng(32.279016, -106.750094))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        rentfrowHall.setTag("Rentfrow Hall");
    }

    public static void setUpNealeHall(GoogleMap map){
        Polygon nealeHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.279962, -106.755644),
                        new LatLng(32.279726, -106.755577),
                        new LatLng(32.279728, -106.755510),
                        new LatLng(32.279320, -106.755392),
                        new LatLng(32.279277, -106.755625),
                        new LatLng(32.279685, -106.755740),
                        new LatLng(32.279694, -106.755727),
                        new LatLng(32.279930, -106.755797))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        nealeHall.setTag("Neale Hall");
    }

    public static void setUpLivestockJudgingPavilion(GoogleMap map){
        Polygon livestockJudgingPavilion = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280196, -106.755789),
                        new LatLng(32.280218, -106.755665),
                        new LatLng(32.280019, -106.755612),
                        new LatLng(32.279996, -106.755740))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        livestockJudgingPavilion.setTag("Livestock Judging Pavilion");
    }

    public static void setUpKnoxHall(GoogleMap map){
        Polygon knoxHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.280529, -106.755794),
                        new LatLng(32.280463, -106.756121),
                        new LatLng(32.280835, -106.756228),
                        new LatLng(32.280887, -106.756400),
                        new LatLng(32.281066, -106.756330),
                        new LatLng(32.280989, -106.756022),
                        new LatLng(32.281001, -106.755939))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        knoxHall.setTag("Knox Hall");
    }

    public static void setUpMusicBuidling(GoogleMap map){
        Polygon musicBuilding = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.283357, -106.755379),
                        new LatLng(32.283468, -106.754746),
                        new LatLng(32.283126, -106.754660),
                        new LatLng(32.283135, -106.754762),
                        new LatLng(32.282933, -106.754703),
                        new LatLng(32.282892, -106.754882),
                        new LatLng(32.282840, -106.754866),
                        new LatLng(32.282770, -106.755231),
                        new LatLng(32.283039, -106.755309),
                        new LatLng(32.283105, -106.754998),
                        new LatLng(32.283293, -106.755051),
                        new LatLng(32.283234, -106.755344))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        musicBuilding.setTag("Music Building");
    }

    public static void setUpChemistryBuidling(GoogleMap map){
        Polygon chemistryBuilding = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.283071, -106.753632),
                        new LatLng(32.282993, -106.754056),
                        new LatLng(32.283226, -106.754131),
                        new LatLng(32.283200, -106.754250),
                        new LatLng(32.283039, -106.754202),
                        new LatLng(32.282968, -106.754525),
                        new LatLng(32.283257, -106.754601),
                        new LatLng(32.283286, -106.754418),
                        new LatLng(32.283215, -106.754403),
                        new LatLng(32.283266, -106.754138),
                        new LatLng(32.283423, -106.754171),
                        new LatLng(32.283475, -106.753908),
                        new LatLng(32.283671, -106.753966),
                        new LatLng(32.283637, -106.754147),
                        new LatLng(32.283555, -106.754134),
                        new LatLng(32.283453, -106.754609),
                        new LatLng(32.283703, -106.754648),
                        new LatLng(32.283821, -106.754161),
                        new LatLng(32.283745, -106.754175),
                        new LatLng(32.283780, -106.753995),
                        new LatLng(32.283897, -106.754020),
                        new LatLng(32.283926, -106.753854))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        chemistryBuilding.setTag("Chemistry Building");
    }


    public static void setUpDoveHall(GoogleMap map){
        Polygon doveHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.283264, -106.752567),
                        new LatLng(32.283297, -106.752392),
                        new LatLng(32.282988, -106.752307),
                        new LatLng(32.282955, -106.752482))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        doveHall.setTag("Dove Hall");
    }


    public static void setUpGuthrieHall(GoogleMap map){
        Polygon guthrieHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.283161, -106.751635),
                        new LatLng(32.283067, -106.751608),
                        new LatLng(32.283076, -106.751573),
                        new LatLng(32.282973, -106.751548),
                        new LatLng(32.282964, -106.751583),
                        new LatLng(32.282879, -106.751563),
                        new LatLng(32.282844, -106.751725),
                        new LatLng(32.282963, -106.751759),
                        new LatLng(32.282948, -106.751847),
                        new LatLng(32.282906, -106.751837),
                        new LatLng(32.282831, -106.752217),
                        new LatLng(32.282966, -106.752220),
                        new LatLng(32.283016, -106.751936),
                        new LatLng(32.283032, -106.751939),
                        new LatLng(32.283057, -106.751856),
                        new LatLng(32.283003, -106.751861),
                        new LatLng(32.283019, -106.751771),
                        new LatLng(32.283132, -106.751797))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        guthrieHall.setTag("Guthrie Hall");
    }


    public static void setUpMiltonHall(GoogleMap map){
        Polygon miltonHall = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.282888, -106.750230),
                        new LatLng(32.282565, -106.750169),
                        new LatLng(32.282338, -106.750296),
                        new LatLng(32.282287, -106.750187),
                        new LatLng(32.282084, -106.750310),
                        new LatLng(32.281963, -106.749970),
                        new LatLng(32.282205, -106.749822),
                        new LatLng(32.282170, -106.749736),
                        new LatLng(32.282387, -106.749608),
                        new LatLng(32.282425, -106.749695),
                        new LatLng(32.282630, -106.749571),
                        new LatLng(32.282999, -106.749682))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        miltonHall.setTag("Milton Hall");
    }


    public static void setUpZuhlLibrary(GoogleMap map){
        Polygon zuhlLibrary = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281513, -106.750061),
                        new LatLng(32.281541, -106.749914),
                        new LatLng(32.281620, -106.749930),
                        new LatLng(32.281735, -106.749334),
                        new LatLng(32.281331, -106.749225),
                        new LatLng(32.281227, -106.749796),
                        new LatLng(32.281298, -106.749817),
                        new LatLng(32.281259, -106.750021))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        zuhlLibrary.setTag("Zuhl Library");
    }


    public static void setUpFrengerFoodCourt(GoogleMap map){
        Polygon frengerFoodCourt = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281086, -106.750661),
                        new LatLng(32.281152, -106.750677),
                        new LatLng(32.281143, -106.750729),
                        new LatLng(32.281204, -106.750744),
                        new LatLng(32.281219, -106.750688),
                        new LatLng(32.281294, -106.750710),
                        new LatLng(32.281277, -106.750824),
                        new LatLng(32.281321, -106.750838),
                        new LatLng(32.281384, -106.750487),
                        new LatLng(32.281274, -106.750450),
                        new LatLng(32.281279, -106.750413),
                        new LatLng(32.281236, -106.750401),
                        new LatLng(32.281235, -106.750377),
                        new LatLng(32.281142, -106.750355))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        frengerFoodCourt.setTag("Frenger Food Court");
    }


    public static void setUpRegentsRow(GoogleMap map){
        Polygon regentsRow = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281069, -106.750066),
                        new LatLng(32.281008, -106.750053),
                        new LatLng(32.281008, -106.750092),
                        new LatLng(32.280769, -106.750030),
                        new LatLng(32.280843, -106.749651),
                        new LatLng(32.280697, -106.749599),
                        new LatLng(32.280624, -106.749977),
                        new LatLng(32.280401, -106.749925),
                        new LatLng(32.280467, -106.749557),
                        new LatLng(32.280326, -106.749494),
                        new LatLng(32.280151, -106.750478),
                        new LatLng(32.280283, -106.750526),
                        new LatLng(32.280363, -106.750122),
                        new LatLng(32.280595, -106.750184),
                        new LatLng(32.280515, -106.750593),
                        new LatLng(32.280657, -106.750628),
                        new LatLng(32.280731, -106.750233),
                        new LatLng(32.280920, -106.750274),
                        new LatLng(32.280914, -106.750356),
                        new LatLng(32.281011, -106.750400))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        regentsRow.setTag("Regents Row");
    }


    public static void setUpHealthCenter(GoogleMap map){
        Polygon healthCenter = map.addPolygon((new PolygonOptions())
                .add(new LatLng(32.281034, -106.749494),
                        new LatLng(32.281105, -106.749171),
                        new LatLng(32.281022, -106.749150),
                        new LatLng(32.281025, -106.749112),
                        new LatLng(32.280852, -106.749077),
                        new LatLng(32.280826, -106.749168),
                        new LatLng(32.280652, -106.749116),
                        new LatLng(32.280676, -106.749015),
                        new LatLng(32.280544, -106.748987),
                        new LatLng(32.280462, -106.749390),
                        new LatLng(32.280588, -106.749425),
                        new LatLng(32.280600, -106.749376))
                .strokeColor(Color.argb(127,0,204,0))
                .fillColor(Color.argb(127,0,204,0)));

        healthCenter.setTag("Health Center");
    }

} // end class
