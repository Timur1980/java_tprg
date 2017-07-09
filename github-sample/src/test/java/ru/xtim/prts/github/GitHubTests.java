package ru.xtim.prts.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by timur.khisamutdinov on 09.07.2017.
 */
public class GitHubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("405e32e152a755d3d7479975ebd9317196c211ef");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("Timur1980", "java_tprg")).commits();
        for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
