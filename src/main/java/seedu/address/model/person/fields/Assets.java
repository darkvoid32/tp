package seedu.address.model.person.fields;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.asset.Asset;

/**
 * Represents a collection of tags associated to a person in the address book.
 */
public class Assets implements Field {

    public static final Prefix PREFIX_ASSET = new Prefix("A/");
    private final Set<Asset> assets;

    /**
     * Constructs a new {@code Assets} from a list of assets.
     *
     * @param assets A list of tags, or null.
     */
    public Assets(Asset... assets) {
        this.assets = Stream.of(assets)
                            .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Constructs a new {@code Assets} from a list of strings.
     * @param assetNames A list of strings, or null.
     */
    public Assets(String... assetNames) {
        this.assets = Stream.of(assetNames)
                            .map(Asset::new)
                            .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Parses {@code Collect<String> names} into a {@code Assets}.
     */
    public static Assets of(Collection<String> names) throws IllegalArgumentException {
        requireNonNull(names);
        return new Assets(names.toArray(String[]::new));
    }

    @JsonValue
    private Set<Asset> get() {
        return assets;
    }

    public boolean contains(Asset asset) {
        return assets.contains(asset);
    }

    /**
     * Changes name of asset.
     * @param target asset to be edited
     * @param editedAsset new asset name
     */
    public Assets edit(Asset target, Asset editedAsset) {
        return new Assets(assets.stream()
                .map(asset -> asset.equals(target) ? editedAsset : asset)
                .toArray(Asset[]::new));
    }

    public Stream<Asset> stream() {
        return assets.stream();
    }

    @Override
    public String toString() {
        return assets.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Assets)) {
            return false;
        }

        Assets otherAssets = (Assets) other;
        return assets.equals(otherAssets.assets);
    }

    @Override
    public int hashCode() {
        return assets.hashCode();
    }
}
