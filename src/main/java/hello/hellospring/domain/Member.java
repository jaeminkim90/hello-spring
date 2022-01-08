package hello.hellospring.domain;

public class Member {

    private Long id; // 시스템에 저장하기 위해 생성하는 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}