package com.diploma.binpacking.service.binpackingimpl;

import net.sourceforge.evoj.strategies.sorting.AbstractSimpleRating;

import java.util.List;

public class BinPackRatingCalculator extends AbstractSimpleRating<Solution, PackRating> {

    private int[] items;
    private int[] bins;

    public BinPackRatingCalculator(int[] items, int[] bins) {
        this.items = items;
        this.bins = bins;
    }


    @Override
    protected PackRating doCalcRating(Solution solution) {

        int[] tmpBins = new int[bins.length];
        int score = 0;
        int overflow = 0;
        int binsUsed = 0;
        final List<Integer> indicex = solution.getBinIndices();
        for (int item = 0; item < indicex.size(); item++) {
            Integer binIndex = indicex.get(item);
            tmpBins[binIndex] += items[item];
        }
        for (int bin = 0; bin < tmpBins.length; bin++) {
            int dl = bins[bin] - tmpBins[bin];
            final int dl2 = dl * dl;
            if (dl < 0) {
                overflow += dl2;
            } else {
                score += dl2;
            }
            if (tmpBins[bin] > 0) {
                binsUsed++;
            }
        }
        return new PackRating(binsUsed, score, overflow);
    }
}
