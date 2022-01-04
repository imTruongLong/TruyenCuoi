package com.teckja.truyencuoi.viewmodel;

import android.content.res.AssetManager;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.teckja.truyencuoi.view.model.StoryModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class M002MainVM extends ViewModel {
    private static final String TAG = M002MainVM.class.getName();

    private List<StoryModel> listStory;
    private String topicName = "Con gái";

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void readStoryFile(AssetManager assetMgr) {
        listStory = new ArrayList<>();
        try {
            InputStream in = assetMgr.open("story/" + topicName + ".txt");
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String name = null;
            StringBuilder content = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                if (name == null) {
                    name = line;
                } else if (line.contains("','0');")) {
                    StoryModel model = new StoryModel(name, content.toString());
                    listStory.add(model);
                    name = null;
                    content = new StringBuilder();
                } else if (!line.isEmpty()) {
                    content.append(line).append("\n");
                }
                line = reader.readLine();
            }
            in.close();
            isr.close();
            reader.close();
            Log.i(TAG, "List story: " + listStory.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<StoryModel> getListStory() {
        return listStory;
    }
}
