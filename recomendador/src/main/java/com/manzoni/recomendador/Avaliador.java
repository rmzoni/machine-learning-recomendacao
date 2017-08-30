package com.manzoni.recomendador;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.common.RandomUtils;

public class Avaliador {
	public static void main(String[] args) throws IOException, TasteException {
		RandomUtils.useTestSeed();
		
		DataModel cursos = new Recomendador().getModeloDeCursos();

		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new RecomendadorDeProdutosBuilder();
		double erro = evaluator.evaluate(builder, null, cursos, 0.9, 1.0);
		System.out.println(erro);	
		
		List<RecommendedItem> recommendations = builder.buildRecommender(cursos).recommend(1, 3);
		for (RecommendedItem recommendation : recommendations) {
		    System.out.println(recommendation);
		}
	}
}
