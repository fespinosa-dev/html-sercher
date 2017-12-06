/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irs.ir_p01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author fespinosa
 */
public class App
{

    public static void main(String[] args) throws IOException
    {

        Path documentFolder = Paths.get("/home/fespinosa/dev/projects/upwork/irs/data/");
        String indexFolder = "/home/fespinosa/dev/projects/upwork/irs/index";
//        String rankingModel = args[2];
//        String query = args[3];

       Indexer indexer = new Indexer(indexFolder);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        indexer.createIndex(documentFolder);
        long endTime = System.currentTimeMillis();
        indexer.close();
    }

}
