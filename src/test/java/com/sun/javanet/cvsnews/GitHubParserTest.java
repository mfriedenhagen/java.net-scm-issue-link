package com.sun.javanet.cvsnews;

import junit.framework.TestCase;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author Kohsuke Kawaguchi
 */
public class GitHubParserTest extends TestCase {
    public void testParse() throws Exception {
        List<GitHubCommit> commits = new GitHubParser().parse(new MimeMessage(Session.getInstance(System.getProperties()),
                getClass().getResourceAsStream("github.txt")));
        assertEquals(2,commits.size());
        assertEquals("a7f34c0a2cb9c05b491abb2307a898c15af42254",commits.get(0).commitSha1);

        GitHubCommit c1 = commits.get(1);
        assertEquals("4207cf93308bc05ab7c4da190bc9d36277d485b6", c1.commitSha1);
        assertEquals(1, c1.getCodeChanges().size());
        assertEquals("changelog.html", c1.getCodeChanges().get(0).fileName);
    }
}