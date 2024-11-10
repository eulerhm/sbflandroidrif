package fr.free.nrw.commons.actions;

import java.lang.System;

/**
 * Response of the Thanks API.
 * Context:
 * The Commons Android app lets you thank other contributors who have uploaded a great picture.
 * See https://www.mediawiki.org/wiki/Extension:Thanks
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0018\u00010\u0004R\u00020\u0000X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lfr/free/nrw/commons/actions/MwThankPostResponse;", "Lorg/wikipedia/dataclient/mwapi/MwResponse;", "()V", "result", "Lfr/free/nrw/commons/actions/MwThankPostResponse$Result;", "getResult", "()Lfr/free/nrw/commons/actions/MwThankPostResponse$Result;", "setResult", "(Lfr/free/nrw/commons/actions/MwThankPostResponse$Result;)V", "Result", "app-commons-v4.2.1-master_prodDebug"})
public final class MwThankPostResponse extends org.wikipedia.dataclient.mwapi.MwResponse {
    @org.jetbrains.annotations.Nullable
    private fr.free.nrw.commons.actions.MwThankPostResponse.Result result;
    
    public MwThankPostResponse() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final fr.free.nrw.commons.actions.MwThankPostResponse.Result getResult() {
        return null;
    }
    
    public final void setResult(@org.jetbrains.annotations.Nullable
    fr.free.nrw.commons.actions.MwThankPostResponse.Result p0) {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lfr/free/nrw/commons/actions/MwThankPostResponse$Result;", "", "(Lfr/free/nrw/commons/actions/MwThankPostResponse;)V", "recipient", "", "getRecipient", "()Ljava/lang/String;", "setRecipient", "(Ljava/lang/String;)V", "success", "", "getSuccess", "()Ljava/lang/Integer;", "setSuccess", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "app-commons-v4.2.1-master_prodDebug"})
    public final class Result {
        @org.jetbrains.annotations.Nullable
        private java.lang.Integer success;
        @org.jetbrains.annotations.Nullable
        private java.lang.String recipient;
        
        public Result() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Integer getSuccess() {
            return null;
        }
        
        public final void setSuccess(@org.jetbrains.annotations.Nullable
        java.lang.Integer p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getRecipient() {
            return null;
        }
        
        public final void setRecipient(@org.jetbrains.annotations.Nullable
        java.lang.String p0) {
        }
    }
}