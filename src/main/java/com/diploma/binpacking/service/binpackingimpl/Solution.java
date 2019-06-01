package com.diploma.binpacking.service.binpackingimpl;

import net.sourceforge.evoj.core.annotation.ListParams;
import net.sourceforge.evoj.core.annotation.MutationRange;
import net.sourceforge.evoj.core.annotation.Range;

import java.util.List;

public interface Solution {

    @ListParams(length = "itemsCount",
            elementRange = @Range(strict = "true", min = "0", max = "lastBinIndex"),
            elementMutationRange = @MutationRange(value = "100%"))
    List<Integer> getBinIndices();
}
