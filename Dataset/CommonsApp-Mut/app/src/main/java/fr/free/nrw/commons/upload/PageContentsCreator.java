package fr.free.nrw.commons.upload;

import android.content.Context;
import androidx.annotation.NonNull;
import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.contributions.Contribution;
import fr.free.nrw.commons.filepicker.UploadableFile.DateTimeWithSource;
import fr.free.nrw.commons.settings.Prefs.Licenses;
import fr.free.nrw.commons.utils.ConfigUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class PageContentsCreator {

    // {{According to Exif data|2009-01-09}}
    private static final String TEMPLATE_DATE_ACC_TO_EXIF = "{{According to Exif data|%s}}";

    // 2009-01-09 → 9 January 2009
    private static final String TEMPLATE_DATA_OTHER_SOURCE = "%s";

    private final Context context;

    @Inject
    public PageContentsCreator(final Context context) {
        this.context = context;
    }

    public String createFrom(final Contribution contribution) {
        StringBuilder buffer = new StringBuilder();
        final Media media = contribution.getMedia();
        if (!ListenerUtil.mutListener.listen(7763)) {
            buffer.append("== {{int:filedesc}} ==\n").append("{{Information\n").append("|description=").append(media.getFallbackDescription()).append("\n");
        }
        if (!ListenerUtil.mutListener.listen(7765)) {
            if (contribution.getWikidataPlace() != null) {
                if (!ListenerUtil.mutListener.listen(7764)) {
                    buffer.append("{{ on Wikidata|").append(contribution.getWikidataPlace().getId()).append("}}");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7766)) {
            buffer.append("\n").append("|source=").append("{{own}}\n").append("|author=[[User:").append(media.getAuthor()).append("|").append(media.getAuthor()).append("]]\n");
        }
        final String templatizedCreatedDate = getTemplatizedCreatedDate(contribution.getDateCreatedString(), contribution.getDateCreated(), contribution.getDateCreatedSource());
        if (!ListenerUtil.mutListener.listen(7768)) {
            if (!StringUtils.isBlank(templatizedCreatedDate)) {
                if (!ListenerUtil.mutListener.listen(7767)) {
                    buffer.append("|date=").append(templatizedCreatedDate);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7769)) {
            buffer.append("}}").append("\n");
        }
        // Only add Location template (e.g. {{Location|37.51136|-77.602615}} ) if coords is not null
        final String decimalCoords = contribution.getDecimalCoords();
        if (!ListenerUtil.mutListener.listen(7771)) {
            if (decimalCoords != null) {
                if (!ListenerUtil.mutListener.listen(7770)) {
                    buffer.append("{{Location|").append(decimalCoords).append("}}").append("\n");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7774)) {
            if ((ListenerUtil.mutListener.listen(7772) ? (contribution.getWikidataPlace() != null || contribution.getWikidataPlace().isMonumentUpload()) : (contribution.getWikidataPlace() != null && contribution.getWikidataPlace().isMonumentUpload()))) {
                if (!ListenerUtil.mutListener.listen(7773)) {
                    buffer.append(String.format(Locale.ENGLISH, "{{Wiki Loves Monuments %d|1= %s}}\n", Utils.getWikiLovesMonumentsYear(Calendar.getInstance()), contribution.getCountryCode()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7775)) {
            buffer.append("== {{int:license-header}} ==\n").append(licenseTemplateFor(media.getLicense())).append("\n\n").append("{{Uploaded from Mobile|platform=Android|version=").append(ConfigUtils.getVersionNameWithSha(context)).append("}}\n");
        }
        final List<String> categories = media.getCategories();
        if (!ListenerUtil.mutListener.listen(7790)) {
            if ((ListenerUtil.mutListener.listen(7781) ? (categories != null || (ListenerUtil.mutListener.listen(7780) ? (categories.size() >= 0) : (ListenerUtil.mutListener.listen(7779) ? (categories.size() <= 0) : (ListenerUtil.mutListener.listen(7778) ? (categories.size() > 0) : (ListenerUtil.mutListener.listen(7777) ? (categories.size() < 0) : (ListenerUtil.mutListener.listen(7776) ? (categories.size() == 0) : (categories.size() != 0))))))) : (categories != null && (ListenerUtil.mutListener.listen(7780) ? (categories.size() >= 0) : (ListenerUtil.mutListener.listen(7779) ? (categories.size() <= 0) : (ListenerUtil.mutListener.listen(7778) ? (categories.size() > 0) : (ListenerUtil.mutListener.listen(7777) ? (categories.size() < 0) : (ListenerUtil.mutListener.listen(7776) ? (categories.size() == 0) : (categories.size() != 0))))))))) {
                if (!ListenerUtil.mutListener.listen(7789)) {
                    {
                        long _loopCounter123 = 0;
                        for (int i = 0; (ListenerUtil.mutListener.listen(7788) ? (i >= categories.size()) : (ListenerUtil.mutListener.listen(7787) ? (i <= categories.size()) : (ListenerUtil.mutListener.listen(7786) ? (i > categories.size()) : (ListenerUtil.mutListener.listen(7785) ? (i != categories.size()) : (ListenerUtil.mutListener.listen(7784) ? (i == categories.size()) : (i < categories.size())))))); i++) {
                            ListenerUtil.loopListener.listen("_loopCounter123", ++_loopCounter123);
                            if (!ListenerUtil.mutListener.listen(7783)) {
                                buffer.append("\n[[Category:").append(categories.get(i)).append("]]");
                            }
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(7782)) {
                    buffer.append("{{subst:unc}}");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(7791)) {
            Timber.d("Template: %s", buffer.toString());
        }
        return buffer.toString();
    }

    /**
     * Returns upload date in either TEMPLATE_DATE_ACC_TO_EXIF or TEMPLATE_DATA_OTHER_SOURCE
     *
     * @param dateCreated
     * @param dateCreatedSource
     * @return
     */
    private String getTemplatizedCreatedDate(String dateCreatedString, Date dateCreated, String dateCreatedSource) {
        if (!ListenerUtil.mutListener.listen(7792)) {
            if (dateCreated != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return String.format(Locale.ENGLISH, isExif(dateCreatedSource) ? TEMPLATE_DATE_ACC_TO_EXIF : TEMPLATE_DATA_OTHER_SOURCE, isExif(dateCreatedSource) ? dateCreatedString : dateFormat.format(dateCreated)) + "\n";
            }
        }
        return "";
    }

    private boolean isExif(String dateCreatedSource) {
        return DateTimeWithSource.EXIF_SOURCE.equals(dateCreatedSource);
    }

    @NonNull
    private String licenseTemplateFor(String license) {
        switch(license) {
            case Licenses.CC_BY_3:
                return "{{self|cc-by-3.0}}";
            case Licenses.CC_BY_4:
                return "{{self|cc-by-4.0}}";
            case Licenses.CC_BY_SA_3:
                return "{{self|cc-by-sa-3.0}}";
            case Licenses.CC_BY_SA_4:
                return "{{self|cc-by-sa-4.0}}";
            case Licenses.CC0:
                return "{{self|cc-zero}}";
        }
        throw new RuntimeException("Unrecognized license value: " + license);
    }
}
