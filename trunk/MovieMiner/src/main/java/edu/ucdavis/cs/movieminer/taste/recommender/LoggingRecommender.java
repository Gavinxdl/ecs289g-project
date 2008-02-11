/**
 * 
 */
package edu.ucdavis.cs.movieminer.taste.recommender;

import java.util.List;

import org.apache.log4j.Logger;

import com.planetj.taste.common.TasteException;
import com.planetj.taste.model.DataModel;
import com.planetj.taste.model.Item;
import com.planetj.taste.recommender.RecommendedItem;
import com.planetj.taste.recommender.Recommender;
import com.planetj.taste.recommender.Rescorer;

/**
 * Decorates the recommender with added logging to 
 * estimatePreference and recommend methods.
 * 
 * 
 * @author jbeck
 *
 */
public class LoggingRecommender extends RecommenderDecorator {

	private static final Logger logger = Logger.getLogger(LoggingRecommender.class);
	
	public LoggingRecommender(Recommender recommender) {
		super(recommender);
	}

	@Override
	public double estimatePreference(Object userID, Object itemID)
			throws TasteException {
		logger.info("Estimating preference for userId: "+userID+" for itemId: "+itemID);
		double preference = decoratedRecommender.estimatePreference(userID, itemID);
		logger.info("Estimated perference: "+preference);
		return preference;
	}

	@Override
	public List<RecommendedItem> recommend(Object userID, int howMany,
			Rescorer<Item> rescorer) throws TasteException {
		logger.info("Recommending item(s) for userId: "+userID);
		return decoratedRecommender.recommend(userID, howMany, rescorer);
	}

	@Override
	public List<RecommendedItem> recommend(Object userID, int howMany)
			throws TasteException {
		logger.info("Recommending item(s) for userId: "+userID);
		return decoratedRecommender.recommend(userID, howMany);
	}

	@Override
	public DataModel getDataModel() {
		return decoratedRecommender.getDataModel();
	}

	@Override
	public void removePreference(Object userID, Object itemID)
			throws TasteException {
		decoratedRecommender.removePreference(userID, itemID);
	}

	@Override
	public void setPreference(Object userID, Object itemID, double value)
			throws TasteException {
		decoratedRecommender.setPreference(userID, itemID, value);
	}

	@Override
	public void refresh() {
		decoratedRecommender.refresh();
	}
}
