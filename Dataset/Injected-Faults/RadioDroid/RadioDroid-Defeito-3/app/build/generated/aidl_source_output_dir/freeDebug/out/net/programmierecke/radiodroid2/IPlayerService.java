/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package net.programmierecke.radiodroid2;
public interface IPlayerService extends android.os.IInterface
{
  /** Default implementation for IPlayerService. */
  public static class Default implements net.programmierecke.radiodroid2.IPlayerService
  {
    @Override public void SetStation(net.programmierecke.radiodroid2.station.DataRadioStation station) throws android.os.RemoteException
    {
    }
    @Override public void Play(boolean isAlarm) throws android.os.RemoteException
    {
    }
    @Override public void Pause(net.programmierecke.radiodroid2.service.PauseReason pauseReason) throws android.os.RemoteException
    {
    }
    @Override public void Resume() throws android.os.RemoteException
    {
    }
    @Override public void Stop() throws android.os.RemoteException
    {
    }
    @Override public void SkipToNext() throws android.os.RemoteException
    {
    }
    @Override public void SkipToPrevious() throws android.os.RemoteException
    {
    }
    @Override public void addTimer(int secondsAdd) throws android.os.RemoteException
    {
    }
    @Override public void clearTimer() throws android.os.RemoteException
    {
    }
    @Override public long getTimerSeconds() throws android.os.RemoteException
    {
      return 0L;
    }
    @Override public java.lang.String getCurrentStationID() throws android.os.RemoteException
    {
      return null;
    }
    @Override public net.programmierecke.radiodroid2.station.DataRadioStation getCurrentStation() throws android.os.RemoteException
    {
      return null;
    }
    @Override public net.programmierecke.radiodroid2.station.live.StreamLiveInfo getMetadataLive() throws android.os.RemoteException
    {
      return null;
    }
    @Override public net.programmierecke.radiodroid2.station.live.ShoutcastInfo getShoutcastInfo() throws android.os.RemoteException
    {
      return null;
    }
    @Override public android.support.v4.media.session.MediaSessionCompat.Token getMediaSessionToken() throws android.os.RemoteException
    {
      return null;
    }
    @Override public boolean isPlaying() throws android.os.RemoteException
    {
      return false;
    }
    @Override public net.programmierecke.radiodroid2.players.PlayState getPlayerState() throws android.os.RemoteException
    {
      return null;
    }
    @Override public void startRecording() throws android.os.RemoteException
    {
    }
    @Override public void stopRecording() throws android.os.RemoteException
    {
    }
    @Override public boolean isRecording() throws android.os.RemoteException
    {
      return false;
    }
    @Override public java.lang.String getCurrentRecordFileName() throws android.os.RemoteException
    {
      return null;
    }
    @Override public long getTransferredBytes() throws android.os.RemoteException
    {
      return 0L;
    }
    @Override public long getBufferedSeconds() throws android.os.RemoteException
    {
      return 0L;
    }
    @Override public long getLastPlayStartTime() throws android.os.RemoteException
    {
      return 0L;
    }
    @Override public boolean getIsHls() throws android.os.RemoteException
    {
      return false;
    }
    @Override public net.programmierecke.radiodroid2.service.PauseReason getPauseReason() throws android.os.RemoteException
    {
      return null;
    }
    @Override public boolean isNotificationActive() throws android.os.RemoteException
    {
      return false;
    }
    @Override public void enableMPD(java.lang.String hostname, int port) throws android.os.RemoteException
    {
    }
    @Override public void disableMPD() throws android.os.RemoteException
    {
    }
    @Override public void warnAboutMeteredConnection(net.programmierecke.radiodroid2.players.selector.PlayerType playerType) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements net.programmierecke.radiodroid2.IPlayerService
  {
    private static final java.lang.String DESCRIPTOR = "net.programmierecke.radiodroid2.IPlayerService";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an net.programmierecke.radiodroid2.IPlayerService interface,
     * generating a proxy if needed.
     */
    public static net.programmierecke.radiodroid2.IPlayerService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof net.programmierecke.radiodroid2.IPlayerService))) {
        return ((net.programmierecke.radiodroid2.IPlayerService)iin);
      }
      return new net.programmierecke.radiodroid2.IPlayerService.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_SetStation:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.station.DataRadioStation _arg0;
          if ((0!=data.readInt())) {
            _arg0 = net.programmierecke.radiodroid2.station.DataRadioStation.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.SetStation(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_Play:
        {
          data.enforceInterface(descriptor);
          boolean _arg0;
          _arg0 = (0!=data.readInt());
          this.Play(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_Pause:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.service.PauseReason _arg0;
          if ((0!=data.readInt())) {
            _arg0 = net.programmierecke.radiodroid2.service.PauseReason.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.Pause(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_Resume:
        {
          data.enforceInterface(descriptor);
          this.Resume();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_Stop:
        {
          data.enforceInterface(descriptor);
          this.Stop();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_SkipToNext:
        {
          data.enforceInterface(descriptor);
          this.SkipToNext();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_SkipToPrevious:
        {
          data.enforceInterface(descriptor);
          this.SkipToPrevious();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_addTimer:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          this.addTimer(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_clearTimer:
        {
          data.enforceInterface(descriptor);
          this.clearTimer();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_getTimerSeconds:
        {
          data.enforceInterface(descriptor);
          long _result = this.getTimerSeconds();
          reply.writeNoException();
          reply.writeLong(_result);
          return true;
        }
        case TRANSACTION_getCurrentStationID:
        {
          data.enforceInterface(descriptor);
          java.lang.String _result = this.getCurrentStationID();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_getCurrentStation:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.station.DataRadioStation _result = this.getCurrentStation();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_getMetadataLive:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.station.live.StreamLiveInfo _result = this.getMetadataLive();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_getShoutcastInfo:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.station.live.ShoutcastInfo _result = this.getShoutcastInfo();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_getMediaSessionToken:
        {
          data.enforceInterface(descriptor);
          android.support.v4.media.session.MediaSessionCompat.Token _result = this.getMediaSessionToken();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_isPlaying:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.isPlaying();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getPlayerState:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.players.PlayState _result = this.getPlayerState();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_startRecording:
        {
          data.enforceInterface(descriptor);
          this.startRecording();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_stopRecording:
        {
          data.enforceInterface(descriptor);
          this.stopRecording();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_isRecording:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.isRecording();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getCurrentRecordFileName:
        {
          data.enforceInterface(descriptor);
          java.lang.String _result = this.getCurrentRecordFileName();
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        case TRANSACTION_getTransferredBytes:
        {
          data.enforceInterface(descriptor);
          long _result = this.getTransferredBytes();
          reply.writeNoException();
          reply.writeLong(_result);
          return true;
        }
        case TRANSACTION_getBufferedSeconds:
        {
          data.enforceInterface(descriptor);
          long _result = this.getBufferedSeconds();
          reply.writeNoException();
          reply.writeLong(_result);
          return true;
        }
        case TRANSACTION_getLastPlayStartTime:
        {
          data.enforceInterface(descriptor);
          long _result = this.getLastPlayStartTime();
          reply.writeNoException();
          reply.writeLong(_result);
          return true;
        }
        case TRANSACTION_getIsHls:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.getIsHls();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getPauseReason:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.service.PauseReason _result = this.getPauseReason();
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_isNotificationActive:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.isNotificationActive();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_enableMPD:
        {
          data.enforceInterface(descriptor);
          java.lang.String _arg0;
          _arg0 = data.readString();
          int _arg1;
          _arg1 = data.readInt();
          this.enableMPD(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_disableMPD:
        {
          data.enforceInterface(descriptor);
          this.disableMPD();
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_warnAboutMeteredConnection:
        {
          data.enforceInterface(descriptor);
          net.programmierecke.radiodroid2.players.selector.PlayerType _arg0;
          if ((0!=data.readInt())) {
            _arg0 = net.programmierecke.radiodroid2.players.selector.PlayerType.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          this.warnAboutMeteredConnection(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements net.programmierecke.radiodroid2.IPlayerService
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void SetStation(net.programmierecke.radiodroid2.station.DataRadioStation station) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((station!=null)) {
            _data.writeInt(1);
            station.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_SetStation, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().SetStation(station);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void Play(boolean isAlarm) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(((isAlarm)?(1):(0)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_Play, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().Play(isAlarm);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void Pause(net.programmierecke.radiodroid2.service.PauseReason pauseReason) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((pauseReason!=null)) {
            _data.writeInt(1);
            pauseReason.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_Pause, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().Pause(pauseReason);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void Resume() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_Resume, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().Resume();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void Stop() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_Stop, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().Stop();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void SkipToNext() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_SkipToNext, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().SkipToNext();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void SkipToPrevious() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_SkipToPrevious, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().SkipToPrevious();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void addTimer(int secondsAdd) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(secondsAdd);
          boolean _status = mRemote.transact(Stub.TRANSACTION_addTimer, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().addTimer(secondsAdd);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void clearTimer() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_clearTimer, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().clearTimer();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public long getTimerSeconds() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        long _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getTimerSeconds, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getTimerSeconds();
          }
          _reply.readException();
          _result = _reply.readLong();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String getCurrentStationID() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getCurrentStationID, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getCurrentStationID();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public net.programmierecke.radiodroid2.station.DataRadioStation getCurrentStation() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        net.programmierecke.radiodroid2.station.DataRadioStation _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getCurrentStation, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getCurrentStation();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = net.programmierecke.radiodroid2.station.DataRadioStation.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public net.programmierecke.radiodroid2.station.live.StreamLiveInfo getMetadataLive() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        net.programmierecke.radiodroid2.station.live.StreamLiveInfo _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getMetadataLive, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getMetadataLive();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = net.programmierecke.radiodroid2.station.live.StreamLiveInfo.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public net.programmierecke.radiodroid2.station.live.ShoutcastInfo getShoutcastInfo() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        net.programmierecke.radiodroid2.station.live.ShoutcastInfo _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getShoutcastInfo, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getShoutcastInfo();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = net.programmierecke.radiodroid2.station.live.ShoutcastInfo.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public android.support.v4.media.session.MediaSessionCompat.Token getMediaSessionToken() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        android.support.v4.media.session.MediaSessionCompat.Token _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getMediaSessionToken, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getMediaSessionToken();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = android.support.v4.media.session.MediaSessionCompat.Token.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean isPlaying() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isPlaying, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isPlaying();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public net.programmierecke.radiodroid2.players.PlayState getPlayerState() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        net.programmierecke.radiodroid2.players.PlayState _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getPlayerState, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getPlayerState();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = net.programmierecke.radiodroid2.players.PlayState.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void startRecording() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_startRecording, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().startRecording();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void stopRecording() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopRecording, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().stopRecording();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public boolean isRecording() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isRecording, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isRecording();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.lang.String getCurrentRecordFileName() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getCurrentRecordFileName, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getCurrentRecordFileName();
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public long getTransferredBytes() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        long _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getTransferredBytes, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getTransferredBytes();
          }
          _reply.readException();
          _result = _reply.readLong();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public long getBufferedSeconds() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        long _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getBufferedSeconds, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getBufferedSeconds();
          }
          _reply.readException();
          _result = _reply.readLong();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public long getLastPlayStartTime() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        long _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getLastPlayStartTime, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getLastPlayStartTime();
          }
          _reply.readException();
          _result = _reply.readLong();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean getIsHls() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getIsHls, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getIsHls();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public net.programmierecke.radiodroid2.service.PauseReason getPauseReason() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        net.programmierecke.radiodroid2.service.PauseReason _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getPauseReason, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getPauseReason();
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = net.programmierecke.radiodroid2.service.PauseReason.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public boolean isNotificationActive() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_isNotificationActive, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().isNotificationActive();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void enableMPD(java.lang.String hostname, int port) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeString(hostname);
          _data.writeInt(port);
          boolean _status = mRemote.transact(Stub.TRANSACTION_enableMPD, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().enableMPD(hostname, port);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void disableMPD() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_disableMPD, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().disableMPD();
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public void warnAboutMeteredConnection(net.programmierecke.radiodroid2.players.selector.PlayerType playerType) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((playerType!=null)) {
            _data.writeInt(1);
            playerType.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_warnAboutMeteredConnection, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().warnAboutMeteredConnection(playerType);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static net.programmierecke.radiodroid2.IPlayerService sDefaultImpl;
    }
    static final int TRANSACTION_SetStation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_Play = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_Pause = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_Resume = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    static final int TRANSACTION_Stop = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
    static final int TRANSACTION_SkipToNext = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
    static final int TRANSACTION_SkipToPrevious = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
    static final int TRANSACTION_addTimer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
    static final int TRANSACTION_clearTimer = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
    static final int TRANSACTION_getTimerSeconds = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
    static final int TRANSACTION_getCurrentStationID = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
    static final int TRANSACTION_getCurrentStation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
    static final int TRANSACTION_getMetadataLive = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
    static final int TRANSACTION_getShoutcastInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
    static final int TRANSACTION_getMediaSessionToken = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
    static final int TRANSACTION_isPlaying = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
    static final int TRANSACTION_getPlayerState = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
    static final int TRANSACTION_startRecording = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
    static final int TRANSACTION_stopRecording = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
    static final int TRANSACTION_isRecording = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
    static final int TRANSACTION_getCurrentRecordFileName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
    static final int TRANSACTION_getTransferredBytes = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
    static final int TRANSACTION_getBufferedSeconds = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
    static final int TRANSACTION_getLastPlayStartTime = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
    static final int TRANSACTION_getIsHls = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
    static final int TRANSACTION_getPauseReason = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
    static final int TRANSACTION_isNotificationActive = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
    static final int TRANSACTION_enableMPD = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
    static final int TRANSACTION_disableMPD = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
    static final int TRANSACTION_warnAboutMeteredConnection = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
    public static boolean setDefaultImpl(net.programmierecke.radiodroid2.IPlayerService impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static net.programmierecke.radiodroid2.IPlayerService getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public void SetStation(net.programmierecke.radiodroid2.station.DataRadioStation station) throws android.os.RemoteException;
  public void Play(boolean isAlarm) throws android.os.RemoteException;
  public void Pause(net.programmierecke.radiodroid2.service.PauseReason pauseReason) throws android.os.RemoteException;
  public void Resume() throws android.os.RemoteException;
  public void Stop() throws android.os.RemoteException;
  public void SkipToNext() throws android.os.RemoteException;
  public void SkipToPrevious() throws android.os.RemoteException;
  public void addTimer(int secondsAdd) throws android.os.RemoteException;
  public void clearTimer() throws android.os.RemoteException;
  public long getTimerSeconds() throws android.os.RemoteException;
  public java.lang.String getCurrentStationID() throws android.os.RemoteException;
  public net.programmierecke.radiodroid2.station.DataRadioStation getCurrentStation() throws android.os.RemoteException;
  public net.programmierecke.radiodroid2.station.live.StreamLiveInfo getMetadataLive() throws android.os.RemoteException;
  public net.programmierecke.radiodroid2.station.live.ShoutcastInfo getShoutcastInfo() throws android.os.RemoteException;
  public android.support.v4.media.session.MediaSessionCompat.Token getMediaSessionToken() throws android.os.RemoteException;
  public boolean isPlaying() throws android.os.RemoteException;
  public net.programmierecke.radiodroid2.players.PlayState getPlayerState() throws android.os.RemoteException;
  public void startRecording() throws android.os.RemoteException;
  public void stopRecording() throws android.os.RemoteException;
  public boolean isRecording() throws android.os.RemoteException;
  public java.lang.String getCurrentRecordFileName() throws android.os.RemoteException;
  public long getTransferredBytes() throws android.os.RemoteException;
  public long getBufferedSeconds() throws android.os.RemoteException;
  public long getLastPlayStartTime() throws android.os.RemoteException;
  public boolean getIsHls() throws android.os.RemoteException;
  public net.programmierecke.radiodroid2.service.PauseReason getPauseReason() throws android.os.RemoteException;
  public boolean isNotificationActive() throws android.os.RemoteException;
  public void enableMPD(java.lang.String hostname, int port) throws android.os.RemoteException;
  public void disableMPD() throws android.os.RemoteException;
  public void warnAboutMeteredConnection(net.programmierecke.radiodroid2.players.selector.PlayerType playerType) throws android.os.RemoteException;
}
