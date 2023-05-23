package rocks.zipcode.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import rocks.zipcode.web.rest.TestUtil;

class LogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Log.class);
        Log log1 = new Log();
        log1.setId(1L);
        Log log2 = new Log();
        log2.setId(log1.getId());
        assertThat(log1).isEqualTo(log2);
        log2.setId(2L);
        assertThat(log1).isNotEqualTo(log2);
        log1.setId(null);
        assertThat(log1).isNotEqualTo(log2);
    }
}
