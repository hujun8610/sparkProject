package com.bupt.bigdata;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

/**
 * Created by hujun on 2015/11/21.
 */
public class WordSegment {
    public static void main(String[] args) {
        String fieldName = "Text";
        String text = "IK Analyzer是一个结合词典分词和文法分词的中文分词开源工具包。它使用了全新的正向迭代最细粒度切分算法。";
        Analyzer analyzer = new IKAnalyzer();

        Directory directory = null;
        IndexWriter iwriter = null;
        IndexReader ireader = null;
        IndexSearcher isearcher = null;

        try {
            directory = new RAMDirectory();
            IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_34,analyzer);
            iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            iwriter = new IndexWriter(directory,iwConfig);
            Document doc = new Document();
            doc.add(new Field("ID","10000",Field.Store.YES,Field.Index.NOT_ANALYZED));
            doc.add(new Field(fieldName, text, Field.Store.YES, Field.Index.ANALYZED));
            iwriter.addDocument(doc);
            iwriter.close();

            ireader = IndexReader.open(directory);
            isearcher = new IndexSearcher(ireader);
            String keyWord = "中文分词工具包";

            QueryParser qp = new QueryParser(Version.LUCENE_34,fieldName,analyzer);
            qp.setDefaultOperator(QueryParser.AND_OPERATOR);
            Query query = qp.parse(keyWord);

            TopDocs topDocs = isearcher.search(query,5);
            System.out.println("命中"+topDocs.totalHits);

            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < topDocs.totalHits; i++) {
                Document targetDoc = isearcher.doc(scoreDocs[i].doc);
                System.out.println("内容"+targetDoc.toString());
            }

        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
           if (ireader !=null){
               try {
                   ireader.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if(directory != null){
               try {
                   directory.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

        }
    }

}
