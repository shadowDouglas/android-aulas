package br.com.wolneyhqf.aulas.appcbr.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.List;

import br.com.wolneyhqf.aulas.appcbr.R;

/**
 * Classe utilitária para criar notificações.
 *
 * http://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */
public class NotificationUtil {

    public static final String ACTION_VISUALIZAR = "receiver_appcbr";

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static void create(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.drawable.ic_notification); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        b.setColor(Color.GREEN);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createBig(Context context, Intent intent, String contentTitle, String contentText,List<String> lines, int id) {
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Configura o estilo Inbox
        int size = lines.size();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(contentTitle);
        for (String s: lines) {
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText(contentText);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.drawable.ic_notification); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela
        b.setNumber(size); // Número para aparecer na notificação
        b.setStyle(inboxStyle); // Estilo customizado

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createWithAction(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.drawable.ic_notification); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // Ação customizada (deixei a mesma intent para os dois)
        PendingIntent actionIntent = PendingIntent.getBroadcast(
                context, 0, new Intent(ACTION_VISUALIZAR), 0);
        b.addAction(R.drawable.ic_acao_pause, "Pause", actionIntent);
        b.addAction(R.drawable.ic_acao_play, "Play", actionIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    // Notification no Android 5.0 Lollipop (Cor vermelha e heads-up e tela de bloqueio)
    public static void createHeadsUpNotification(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.drawable.ic_notification); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // No Android 5.0
        b.setColor(Color.RED);
        // Heads-up notification
        b.setFullScreenIntent(p,false);
        // Privada se estiver na tela de bloqueio
        b.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    // Notification no Android 5.0 Lollipop (tela de bloqueio)
    public static void createPrivateNotification(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.drawable.ic_notification); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // PUBLIC, PRIVATE, SECRET
        b.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createBigImage(Context context, Intent intent, String contentTitle, String contentText, Bitmap bitmap, int id){
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);
        style.setBigContentTitle(contentTitle);
        style.setSummaryText(contentText);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setColor(Color.BLACK);
        builder.setContentTitle(contentTitle);
        builder.setContentText(contentText);
        builder.setContentIntent(p);
        builder.setAutoCancel(true);
        builder.setStyle(style);
        Uri uriSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.complete);
        builder.setSound(uriSound);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(id, builder.build());
    }


    public static void cancel(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancelAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
