/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irs.ir_p01;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;

/**
 *
 * @author fespinosa
 */
public class App {

    public static void main(String[] args) {

        if (!(args.length > 3)) {
            System.out.println("Missing arguments...");
            System.exit(0);
        }

        try {
            Path documentFolder = Paths.get(args[0]);
            Path indexFolder = Paths.get(args[1]);
            String rankingModel = args[2];
            String query = args[3];

            Indexer indexer = new Indexer(indexFolder.toString());
            int numIndexed;
            long startTime = System.currentTimeMillis();
            numIndexed = indexer.createIndex(documentFolder);
            long endTime = System.currentTimeMillis();
            System.out.println(numIndexed + " File indexed, time taken: "
                    + (endTime - startTime) + " ms");
            indexer.close();

            //Perform search
            Searcher searcher = new Searcher(indexFolder, getSimilarity(rankingModel));
            TopDocs hits = searcher.search(query);

            System.out.println(hits.totalHits
                    + " documents found. Time :" + (endTime - startTime));
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = searcher.getDocument(scoreDoc);
                System.out.println("File: "
                        + doc.get("body"));
            }

        } catch (ParseException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Similarity getSimilarity(String similarityType) {
        Similarity similarity = null;
        if ("VS".equalsIgnoreCase(similarityType)) {
            similarity = new ClassicSimilarity();
        }
        if ("OK".equalsIgnoreCase(similarityType)) {
            similarity = new BM25Similarity();
        }
        return similarity;
    }

}
