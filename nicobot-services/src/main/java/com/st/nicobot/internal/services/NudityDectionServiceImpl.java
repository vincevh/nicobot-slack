package com.st.nicobot.internal.services;

import com.algorithmia.Algorithmia;
import com.algorithmia.AlgorithmiaClient;
import com.algorithmia.algo.AlgoResponse;
import com.algorithmia.algo.Algorithm;
import com.st.nicobot.services.NudityDectionService;
import com.st.nicobot.services.PropertiesService;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static com.st.nicobot.utils.NicobotProperty.ALGORITHMIA_API_KEY;
import static com.st.nicobot.utils.NicobotProperty.ALGORITHMIA_NUDITY_ALGORITHM;

/**
 * Created by Logs on 06-01-17.
 */
@Service
public class NudityDectionServiceImpl implements NudityDectionService {

    private static Logger logger = LoggerFactory.getLogger(NudityDectionServiceImpl.class);

    @Autowired
    private PropertiesService properties;

    private Algorithm algo;

    @PostConstruct
    public void init() {
        AlgorithmiaClient client = Algorithmia.client(properties.get(ALGORITHMIA_API_KEY));
        algo = client.algo(properties.get(ALGORITHMIA_NUDITY_ALGORITHM));
    }

    @Override
    public JSONObject checkUrl(String url) throws Exception {
        AlgoResponse result = algo.pipeJson(String.format("\"%s\"", url));
        return new JSONObject(result.asJsonString());
    }
}
