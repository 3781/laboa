package team.oha.laboa.dao;

import team.oha.laboa.model.CooperationAgendaDo;

/**
 * <p></p>
 *
 * @author loser
 * @version 1.0
 * @data 2017/12/7
 * @modified
 */
public interface CooperationAgendaDao {
    Integer save(CooperationAgendaDo cooperationAgendaDo);
    Integer cleanAgendaSummary();
}
