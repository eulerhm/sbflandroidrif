package fr.free.nrw.commons.quiz;

import java.lang.System;

/**
 * class contains information about all the quiz questions
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u0005R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lfr/free/nrw/commons/quiz/QuizQuestion;", "", "questionNumber", "", "question", "", "url", "isAnswer", "", "answerMessage", "(ILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getAnswerMessage", "()Ljava/lang/String;", "setAnswerMessage", "(Ljava/lang/String;)V", "()Z", "setAnswer", "(Z)V", "getQuestion", "setQuestion", "getQuestionNumber", "()I", "setQuestionNumber", "(I)V", "getUrl", "Landroid/net/Uri;", "setUrl", "", "app-commons-v4.2.1-master_prodDebug"})
public final class QuizQuestion {
    private int questionNumber;
    @org.jetbrains.annotations.NotNull
    private java.lang.String question;
    private java.lang.String url;
    private boolean isAnswer;
    @org.jetbrains.annotations.NotNull
    private java.lang.String answerMessage;
    
    public QuizQuestion(int questionNumber, @org.jetbrains.annotations.NotNull
    java.lang.String question, @org.jetbrains.annotations.NotNull
    java.lang.String url, boolean isAnswer, @org.jetbrains.annotations.NotNull
    java.lang.String answerMessage) {
        super();
    }
    
    public final int getQuestionNumber() {
        return 0;
    }
    
    public final void setQuestionNumber(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getQuestion() {
        return null;
    }
    
    public final void setQuestion(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final boolean isAnswer() {
        return false;
    }
    
    public final void setAnswer(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAnswerMessage() {
        return null;
    }
    
    public final void setAnswerMessage(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.net.Uri getUrl() {
        return null;
    }
    
    public final void setUrl(@org.jetbrains.annotations.NotNull
    java.lang.String url) {
    }
}