package server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrik Melander
 * Date: 2020-11-12
 * Time: 13:21
 * Project: Quiskampen
 * Copyright: MIT
 */
public class Database {
    List<Questions> test = new ArrayList<>();
    Questions sara = new Questions("Hur gammal är Sara", "37", "25", "43", "32");
    Questions simon = new Questions("Hur gammal är Simon", "21", "18", "30", "25");
    Questions axel = new Questions("Hur gammal är Axel", "27", "25", "30", "20");
    Questions patrik = new Questions("Hur gammal är Patrik", "35", "40", "30", "25");
}
