package com.github.russ4stall.fourscorepicks.patch.dao;

import java.util.Date;
import java.util.List;

/**
 * Created by russ on 8/14/14.
 */
public interface PatchDao {
    List<String> getCompletedPatches();
    void addCompletedPatch(String name);
}
