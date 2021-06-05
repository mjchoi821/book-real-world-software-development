package com.iteratrlearning.shu_book.chapter_04.dms;

import java.util.Map;

/**
 * 문서 도메인
 * - 불변(일급) 컬렉션
 * - Document 클래스 생성자는 패키지 내에서만 생성할 수 있도록 제한하고 있다.
 *   이 부분은 '계약에 의한 설계(Design By Contract)'와도 관련이 있다고 생각한다.
 *   DBC는 특정한 기능을 제공하는 모듈로서 정상적인 사용(협력)을 위해
 *   서로가 준수해야 하는 제약을 코드상에 명시적으로 표현하고 강제할 수 있는 방법이다.
 */
public class Document {
    private final Map<String, String> attributes;

    Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
}
