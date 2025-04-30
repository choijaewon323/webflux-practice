package com.jaewon.toy.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Query {
    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    public static class QueryBuilder {
        private final StringBuilder sb;

        public QueryBuilder() {
            this.sb = new StringBuilder();
        }

        public QueryBuilder select(String stmt) {
            sb.append("SELECT ");
            sb.append(stmt);
            sb.append(" ");
            return this;
        }

        public QueryBuilder deleteFrom(String deleteFrom) {
            sb.append("DELETE FROM ");
            sb.append(deleteFrom);
            sb.append(" ");
            return this;
        }

        public QueryBuilder eq(String operand) {
            sb.append("= ");
            sb.append(operand);
            sb.append(" ");
            return this;
        }

        public QueryBuilder notEq(String operand) {
            sb.append("!= ");
            sb.append(operand);
            sb.append(" ");
            return this;
        }

        public QueryBuilder or(String operand) {
            sb.append("OR ");
            sb.append(operand);
            sb.append(" ");
            return this;
        }

        public QueryBuilder and(String operand) {
            sb.append("AND ");
            sb.append(operand);
            sb.append(" ");
            return this;
        }

        public QueryBuilder from(String table) {
            sb.append("FROM ");
            sb.append(table);
            sb.append(" ");
            return this;
        }

        public QueryBuilder innerJoin(String join) {
            sb.append("INNER JOIN ");
            sb.append(join);
            sb.append(" ");
            return this;
        }

        public QueryBuilder on(String on) {
            sb.append("ON ");
            sb.append(on);
            sb.append(" ");
            return this;
        }

        public QueryBuilder where(String where) {
            sb.append("WHERE ");
            sb.append(where);
            sb.append(" ");
            return this;
        }

        public QueryBuilder groupBy(String groupBy) {
            sb.append("GROUP BY ");
            sb.append(groupBy);
            sb.append(" ");
            return this;
        }

        public QueryBuilder limit(int limit) {
            sb.append("LIMIT ");
            sb.append(limit);
            sb.append(" ");
            return this;
        }

        public QueryBuilder offset(int offset) {
            sb.append("OFFSET ");
            sb.append(offset);
            sb.append(" ");
            return this;
        }

        public QueryBuilder orderBy(String columns) {
            sb.append("ORDER BY ");
            sb.append(columns);
            sb.append(" ");
            return this;
        }

        public String build() {
            return sb.toString().trim();
        }
    }
}
